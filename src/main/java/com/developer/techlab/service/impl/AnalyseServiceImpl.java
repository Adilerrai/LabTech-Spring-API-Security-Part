package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.*;
import com.developer.techlab.entities.*;
import com.developer.techlab.entities.enums.ResultatTeste;
import com.developer.techlab.repositories.*;
import com.developer.techlab.service.AnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AnalyseRepository analyseRepository;
    @Autowired
    TesteRepository testeRepository;
    @Autowired
    EchantillonRepository echantillonRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AnalyseServiceImpl(AnalyseRepository analyseRepository, TesteRepository testeRepository, EchantillonRepository echantillonRepository, ModelMapper modelMapper, EntityManager entityManager) {
        this.analyseRepository = analyseRepository;
        this.testeRepository = testeRepository;
        this.echantillonRepository = echantillonRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public AnalyseDTO saveAnalyse(AnalyseDTO analyseDTO) {
        Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);
        Echantillon echantillon = echantillonRepository.findById(analyseDTO.getEchantillon().getId())
                .orElseThrow(() -> new EntityNotFoundException("Echantillon not found with id: " + analyseDTO.getEchantillon().getId()));
        if (!entityManager.contains(echantillon)) {
            echantillon = entityManager.merge(echantillon);
        }
        analyse.setEchantillon(echantillon);
        Analyse savedAnalyse = analyseRepository.save(analyse);
        List<Teste> testeList = savedAnalyse.getTestes();
        for (Teste teste : testeList) {
            teste.setAnalyse(savedAnalyse);
            testeRepository.save(teste);
        }
        return modelMapper.map(savedAnalyse, AnalyseDTO.class);
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
            for(Teste t : existingAnalyse.getTestes()){
                if (t.getMin()<t.getValeur() && t.getMax()>t.getValeur()){
                    t.setResultat(ResultatTeste.NORMAL);
                }
                else{
                    t.setResultat(ResultatTeste.ANORMAL);
                }
            }
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
