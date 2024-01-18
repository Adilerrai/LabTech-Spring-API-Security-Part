package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.AnalyseDTO;
import com.developer.techlab.entities.Analyse;
import com.developer.techlab.repositories.AnalyseRepository;
import com.developer.techlab.service.AnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
