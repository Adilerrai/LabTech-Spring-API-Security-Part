package com.developer.techlab.service;

import com.developer.techlab.DTO.AnalyseDTO;

import java.util.List;

public interface AnalyseService {
    AnalyseDTO saveAnalyse(AnalyseDTO analyseDTO);
    AnalyseDTO getAnalyseById(long analyseId);
    List<AnalyseDTO> getAllAnalyses();
    void deleteAnalyse(long analyseId);
    AnalyseDTO updateAnalyse(long analyseId, AnalyseDTO updatedAnalyseDTO);
}
