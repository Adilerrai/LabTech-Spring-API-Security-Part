package com.developer.techlab.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "testes_table")
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "valeur")
    private double valeur;

    @Column(name = "min")
    private double min;

    @Column(name = "max")
    private double max;

    @ManyToOne
    private Analyse analyse;

    @OneToMany(mappedBy = "teste")
    private List<TesteReactif> testeReactifs = new ArrayList<>();

    @OneToOne
    private Resultat resultat;
}
