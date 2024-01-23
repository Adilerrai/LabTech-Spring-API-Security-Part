package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.TesteDTO;
import com.developer.techlab.entities.Teste;
import com.developer.techlab.repositories.TesteRepository;
import com.developer.techlab.service.TesteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 class TestServiceImpl implements TesteService {
    TesteRepository testeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Teste saveTeste(Teste teste) {
        return testeRepository.save(teste) ;
    }

    @Override
    public Teste getTesteById(long testeId) {
        return testeRepository.findById(testeId).orElse(null);
    }

    @Override
    public void deleteTeste(long testeId) {
    }

    @Override
    public TesteDTO updateTeste(long testeId, TesteDTO updatedTesteDTO) {
//        Optional<Teste> optionalTeste = testeRepository.findById(testeId);
//        if (optionalTeste.isPresent()) {
//            Teste existingTeste = optionalTeste.get();
//            modelMapper.map(updatedTesteDTO, existingTeste);
//            return modelMapper.map(testeRepository.save(existingTeste), TesteDTO.class);
//        }
        return null;
    }

    @Override
    @Transactional
    public List<Teste> saveTestes(List<TesteDTO> testesDTO) {
        List<Teste> savedTestes = new ArrayList<>();
        for (TesteDTO testeDTO : testesDTO) {
            Teste teste = modelMapper.map(testeDTO, Teste.class);
            savedTestes.add(testeRepository.save(teste));
        }
        return savedTestes;
    }
}

