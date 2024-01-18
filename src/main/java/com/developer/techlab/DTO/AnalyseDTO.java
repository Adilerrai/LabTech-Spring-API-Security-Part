package com.developer.techlab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseDTO {

    private long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private List<TesteDTO> testes;
    private EchantillonDTO echantillon;
    private UserLabDTO userLab;
    private PatientDTO patient;
    private AnalyseDetailsDTO analyseDetails;
}
