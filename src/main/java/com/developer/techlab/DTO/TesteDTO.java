package com.developer.techlab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TesteDTO {

    private long id;
    private String libelle;
    private double valeur;
    private double min;
    private double max;
    private AnalyseDTO analyseDTO;
    private List<TesteReactifDTO> testeReactifDTOs;
    private ResultatDTO resultatDTO;
    private TesteDetailsDTO testeDetailsDTO;

}
