package com.developer.techlab.DTO;

import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.Patient;
import com.developer.techlab.entities.enums.StatutEchantillon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class EchantillonDTO {


    private long id;

    private LocalDate date_prev;

    private StatutEchantillon statut;

//    private Patient patient;
    @ToString.Exclude

    private List<Analyse> analyses;

}
