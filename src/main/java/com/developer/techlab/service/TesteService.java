package com.developer.techlab.service;

import com.developer.techlab.entities.Teste;

public interface TesteService {
    Teste saveTeste(Teste teste);
    Teste getTesteById(long testeId);
    void deleteTeste(long testeId);
    Teste updateTeste(long testeId, Teste updatedTeste);

}
