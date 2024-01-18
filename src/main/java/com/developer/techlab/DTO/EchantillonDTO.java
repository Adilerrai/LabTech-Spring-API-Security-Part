package com.developer.techlab.DTO;

import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.Patient;
import com.developer.techlab.entities.enums.StatutEchantillon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchantillonDTO {


    private long id;

    private LocalDate date_prev;

    private StatutEchantillon statut;

    private Patient patient;

    private List<Analyse> analyses;

}
