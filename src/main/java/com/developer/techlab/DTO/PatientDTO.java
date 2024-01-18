package com.developer.techlab.DTO;

import com.developer.techlab.entities.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private long id;
    private String nom;
    private String email;
    private String tele;
    private Sexe sexe;

}