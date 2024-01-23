package com.developer.techlab.service;

import com.developer.techlab.DTO.TesteDTO;
import com.developer.techlab.entities.Teste;

import java.util.List;

public interface TesteService {
    Teste saveTeste(Teste teste);
    Teste getTesteById(long testeId);
    void deleteTeste(long testeId);
    TesteDTO updateTeste(long testeId, TesteDTO updatedTesteDTO);
    List<Teste> saveTestes(List<TesteDTO> testesDTO);
}
