package com.developer.techlab.DTO;

import com.developer.techlab.entities.enums.ResultatTeste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    private ResultatTeste resultat;
    private TesteDetailsDTO testeDetailsDTO;

}
