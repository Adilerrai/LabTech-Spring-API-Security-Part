package com.developer.techlab.service.impl;

import com.developer.techlab.entities.Teste;
import com.developer.techlab.repositories.TesteRepository;
import com.developer.techlab.service.TesteService;
import org.springframework.stereotype.Service;

@Service
 class TestServiceImpl implements TesteService {
    TesteRepository testeRepository;

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
    public Teste updateTeste(long testeId, Teste updatedTeste) {
        return null;
    }
}

