package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.EchantillonDTO;
import com.developer.techlab.entities.Echantillon;
import com.developer.techlab.repositories.EchantillonRepository;
import com.developer.techlab.service.EchantillonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EchantillonServiceImpl implements EchantillonService {

    @Autowired
    private EchantillonRepository echantillonRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EchantillonDTO saveEchantillon(EchantillonDTO echantillonDTO) {
        Echantillon echantillon = modelMapper.map(echantillonDTO, Echantillon.class);
        return modelMapper.map(echantillonRepository.save(echantillon), EchantillonDTO.class);
    }

    @Override
    public EchantillonDTO getEchantillonById(long echantillonId) {
        Optional<Echantillon> optionalEchantillon = echantillonRepository.findById(echantillonId);
        return optionalEchantillon.map(echantillon -> modelMapper.map(echantillon, EchantillonDTO.class)).orElse(null);
    }

    @Override
    public List<EchantillonDTO> getAllEchantillons() {
        List<Echantillon> echantillons = echantillonRepository.findAll();
        return echantillons.stream()
                .map(echantillon -> modelMapper.map(echantillon, EchantillonDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEchantillon(long echantillonId) {
        echantillonRepository.deleteById(echantillonId);
    }

    @Override
    public EchantillonDTO updateEchantillon(long echantillonId, EchantillonDTO updatedEchantillonDTO) {
        Optional<Echantillon> optionalEchantillon = echantillonRepository.findById(echantillonId);
        if (optionalEchantillon.isPresent()) {
            Echantillon existingEchantillon = optionalEchantillon.get();
            modelMapper.map(updatedEchantillonDTO, existingEchantillon);
            return modelMapper.map(echantillonRepository.save(existingEchantillon), EchantillonDTO.class);
        }
        return null;
    }
}


