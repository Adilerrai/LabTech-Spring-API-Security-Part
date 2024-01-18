package com.developer.techlab.DTO;

import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.Echantillon;
import com.developer.techlab.entities.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private long id;
    private String nom;
    private String email;
    private String tele;
    private Sexe sexe;
    @ToString.Exclude
    private List<Echantillon> echantillons;
    @ToString.Exclude
    private List<Analyse> analyses;

}