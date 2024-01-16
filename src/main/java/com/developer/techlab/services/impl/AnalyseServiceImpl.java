package com.developer.techlab.services.impl;

import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.enums.StatutEchantillon;
import com.developer.techlab.repositories.AnalyseRepository;
import com.developer.techlab.services.IAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyseServiceImpl implements IAnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    @Override
    public List<Analyse> getAllOngoingAnalyses() {
        return analyseRepository.findByDateFinAfterAndEchantillonStatut(LocalDate.now(), StatutEchantillon.EN_COURS_ANALYSE);
    }
}
