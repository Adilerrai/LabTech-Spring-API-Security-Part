package com.developer.techlab.DTO;

import com.developer.techlab.entities.Teste;
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
    private ReactifDTO reactif;
    private List<TesteDTO> testeDTOs;

    public AnalyseDTO(String libelle, LocalDate date_debut, LocalDate date_fin, List<Teste> testes, EchantillonDTO echantillon, UserLabDTO userLab, PatientDTO patient, ReactifDTO reactif) {
        this.libelle = libelle;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.testes = testes;
        this.echantillon = echantillon;
        this.userLab = userLab;
        this.patient = patient;
        this.reactif = reactif;
    }

    public AnalyseDTO(long id, String libelle, LocalDate date_debut, LocalDate date_fin, List<Teste> testes, EchantillonDTO echantillon, UserLabDTO userLab, PatientDTO patient, ReactifDTO reactif) {
        this.id = id;
        this.libelle = libelle;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.testes = testes;
        this.echantillon = echantillon;
        this.userLab = userLab;
        this.patient = patient;
        this.reactif = reactif;
    }
}
