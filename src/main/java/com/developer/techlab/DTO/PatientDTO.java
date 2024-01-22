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

    public PatientDTO(long id, String nom, String email, String tele, Sexe sexe, List<Echantillon> echantillons) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.tele = tele;
        this.sexe = sexe;
        this.echantillons = echantillons;
    }

    private Sexe sexe;
    @ToString.Exclude
    private List<Echantillon> echantillons;
    @ToString.Exclude
    private List<Analyse> analyses;

    public PatientDTO(long id) {
        this.id = id;
    }
}