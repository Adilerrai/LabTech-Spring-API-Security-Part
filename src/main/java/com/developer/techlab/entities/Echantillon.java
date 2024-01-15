package com.developer.techlab.entities;

import com.developer.techlab.entities.enums.StatutEchantillon;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "echantillon_table")
public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_prev")
    private LocalDate date_prev;

    @Column(name = "statut")
    private StatutEchantillon statut;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Analyse analyse;

    @OneToMany(mappedBy = "echantillon")
    private List<Analyse> analyses;

}
