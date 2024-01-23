package com.developer.techlab.DTO;

import com.developer.techlab.entities.*;
import lombok.*;

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
    private Echantillon echantillon;
    private UserLab userLab;
    private Patient patient;
    private AnalyseDetails analyseDetails;
    private Reactif reactif;

    public AnalyseDTO(String libelle, LocalDate date_debut, LocalDate date_fin, List<Teste> testes, Echantillon echantillon, UserLab userLab, Patient patient, Reactif reactif) {
        this.libelle = libelle;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.testes = testes;
        this.echantillon = echantillon;
        this.userLab = userLab;
        this.patient = patient;
        this.reactif = reactif;
    }

    public AnalyseDTO(long id, String libelle, LocalDate date_debut, LocalDate date_fin, List<Teste> testes, Echantillon echantillon, UserLab userLab, Patient patient, Reactif reactif) {
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

    public AnalyseDTO(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public AnalyseDTO(long id, String analyseLibelle, LocalDate of, LocalDate of1, List<Teste> list, EchantillonDTO echantillonDTO, UserLabDTO userLabDTO, PatientDTO patientDTO, ReactifDTO reactifDTO) {

    }

    public AnalyseDTO(String analyseLibelle, LocalDate of, LocalDate of1, List<Teste> list, EchantillonDTO echantillonDTO, UserLab userLabDTO, PatientDTO patientDTO, ReactifDTO reactifDTO) {
    }

    public <T> AnalyseDTO(long l, String analyseLibelle, LocalDate of, LocalDate of1, List<T> list, EchantillonDTO echantillonDTO, UserLab userLabDTO, PatientDTO patientDTO, ReactifDTO reactifDTO) {
    }
}
