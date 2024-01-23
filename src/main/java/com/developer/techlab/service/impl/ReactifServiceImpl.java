package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.ReactifDTO;
import com.developer.techlab.entities.Reactif;
import com.developer.techlab.repositories.ReactifRepository;
import com.developer.techlab.service.ReactifService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReactifServiceImpl implements ReactifService {

    @Autowired
    private ReactifRepository reactifRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReactifDTO saveReactif(ReactifDTO reactifDTO) {
        Reactif reactif = modelMapper.map(reactifDTO, Reactif.class);
        return modelMapper.map(reactifRepository.save(reactif), ReactifDTO.class);
    }

    @Override
    public ReactifDTO getReactifById(long reactifId) {
        Reactif reactif = reactifRepository.findById(reactifId).orElse(null);
        return (reactif != null) ? modelMapper.map(reactif, ReactifDTO.class) : null;
    }

    @Override
    public List<ReactifDTO> getAllReactifs() {
        List<Reactif> reactifs = reactifRepository.findAll();
        return reactifs.stream()
                .map(reactif -> modelMapper.map(reactif, ReactifDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReactif(long reactifId) {
        reactifRepository.deleteById(reactifId);
    }

    @Override
    public ReactifDTO updateReactif(long reactifId, ReactifDTO updatedReactifDTO) {
        Reactif existingReactif = reactifRepository.findById(reactifId).orElse(null);
        if (existingReactif != null) {
            modelMapper.map(updatedReactifDTO, existingReactif);
            return modelMapper.map(reactifRepository.save(existingReactif), ReactifDTO.class);
        }
        return null;
    }

    @Override
    public int getReactifQuantity(long reactifId) {
        Reactif existingReactif = reactifRepository.findById(reactifId).orElse(null);
        if (existingReactif != null) {
            return existingReactif.getQuantite();
        }
        return 0;

    }
}
