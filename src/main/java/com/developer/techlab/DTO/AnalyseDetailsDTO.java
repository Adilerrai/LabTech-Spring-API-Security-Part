package com.developer.techlab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseDetailsDTO {

    private long id;
    private String libelle;
    private List<TesteDetailsDTO> testeDetailsDTOs;
    private List<AnalyseDTO> analyseDTOs;


}
