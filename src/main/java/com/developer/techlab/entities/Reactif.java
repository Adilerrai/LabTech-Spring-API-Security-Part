package com.developer.techlab.entities;

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
@Table(name = "reactif_table")
public class Reactif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "date_exp")
    private LocalDate date_exp;

    @Column(name = "fournisseur")
    private String fournisseur;

    @OneToMany(mappedBy = "reactif")
    private List<TesteReactif> testeReactifs = new ArrayList<>();

}
