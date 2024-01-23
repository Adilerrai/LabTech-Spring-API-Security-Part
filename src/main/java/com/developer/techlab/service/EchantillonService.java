package com.developer.techlab.service;

import com.developer.techlab.DTO.EchantillonDTO;

import java.util.List;

public interface EchantillonService {
    EchantillonDTO saveEchantillon(EchantillonDTO echantillonDTO);
    EchantillonDTO getEchantillonById(long echantillonId);
    List<EchantillonDTO> getAllEchantillons(); // Corrected return type
    void deleteEchantillon(long echantillonId);
    EchantillonDTO updateEchantillon(long echantillonId, EchantillonDTO updatedEchantillonDTO);
}
