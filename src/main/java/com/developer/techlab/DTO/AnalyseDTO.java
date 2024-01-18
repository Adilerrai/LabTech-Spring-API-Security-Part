package com.developer.techlab.DTO;

import com.developer.techlab.entities.Teste;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseDTO {

    private long id;
    private String libelle;
    private LocalDate date_debut;
    private LocalDate date_fin;
    @ToString.Exclude
    private List<Teste> testes;
    private EchantillonDTO echantillon;
    private UserLabDTO userLab;
    private PatientDTO patient;
    private AnalyseDetailsDTO analyseDetails;
}
