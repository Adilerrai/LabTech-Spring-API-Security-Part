package com.developer.techlab.entities;


import com.developer.techlab.entities.enums.ResultatTeste;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="min")
    private double min;

    @Column(name = "max")
    private double max;

    @Column(name = "resultat")
    private ResultatTeste resultat;

    @ManyToOne
    private Analyse analyse;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "teste", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TesteReactif> testeReactifs = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    private Resultat resultat;

    @ManyToOne
    private TesteDetails testeDetails;

    public Teste(String libelle, double valeur, double min, double max) {
        this.libelle = libelle;
        this.valeur = valeur;
        this.min = min;
        this.max = max;
    }
}
