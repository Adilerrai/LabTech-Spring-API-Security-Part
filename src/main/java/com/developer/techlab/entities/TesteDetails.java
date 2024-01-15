package com.developer.techlab.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "teste_details_table")
public class TesteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "min")
    private double min;

    @Column(name = "max")
    private double max;

    @ManyToOne
    private AnalyseDetails analyseDetails;
}
