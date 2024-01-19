package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.AnalyseDTO;
import com.developer.techlab.DTO.TesteDTO;
import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.AnalyseDetails;
import com.developer.techlab.entities.Teste;
import com.developer.techlab.entities.TesteDetails;
import com.developer.techlab.repositories.AnalyseRepository;
import com.developer.techlab.service.AnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnalyseDTO saveAnalyse(AnalyseDTO analyseDTO) {
        Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);
        return modelMapper.map(analyseRepository.save(analyse), AnalyseDTO.class);
    }

    @Override
    public AnalyseDTO getAnalyseById(long analyseId) {
        Optional<Analyse> optionalAnalyse = analyseRepository.findById(analyseId);
        return optionalAnalyse.map(analyse -> modelMapper.map(analyse, AnalyseDTO.class)).orElse(null);
    }

    @Override
    public List<AnalyseDTO> getAllAnalyses() {
        List<Analyse> analyses = analyseRepository.findAll();
        return analyses.stream()
                .map(analyse -> modelMapper.map(analyse, AnalyseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAnalyse(long analyseId) {
        analyseRepository.deleteById(analyseId);
    }

    @Override
    public AnalyseDTO updateAnalyse(long analyseId, AnalyseDTO updatedAnalyseDTO) {
        Optional<Analyse> optionalAnalyse = analyseRepository.findById(analyseId);
        if (optionalAnalyse.isPresent()) {
            Analyse existingAnalyse = optionalAnalyse.get();
            modelMapper.map(updatedAnalyseDTO, existingAnalyse);
            return modelMapper.map(analyseRepository.save(existingAnalyse), AnalyseDTO.class);
        }
        return null;
    }

    @Override
    public List<AnalyseDTO> getOngoingAnalyses() {
        LocalDate currentDate = LocalDate.now();
        List<Analyse> ongoingAnalyses = analyseRepository.findOngoingAnalyses(currentDate);

        return ongoingAnalyses.stream()
                .map(analyse -> modelMapper.map(analyse, AnalyseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TesteDTO> getTestResultsForAnalyse(long analyseId) {
        Analyse analyse = analyseRepository.findById(analyseId).orElse(null);
        return analyse.getTestes().stream()
                .map(teste -> modelMapper.map(teste, TesteDTO.class))
                .collect(Collectors.toList());
    }

//    public void add(Analyse analyse){
//        AnalyseDetails analyseDetails = analyse.getAnalyseDetails();
//        List<TesteDetails> testeDetailsList = analyseDetails.getTesteDetails();
//        for(TesteDetails t: testeDetailsList){
//            Teste teste = new Teste();
//            teste.setAnalyse(analyse);
//            teste.setTesteDetails(t);
//        }
//    }
}
