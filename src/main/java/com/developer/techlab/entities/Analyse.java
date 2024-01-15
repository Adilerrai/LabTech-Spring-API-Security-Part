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
@Table(name = "analyse_table")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "date_debut")
    private LocalDate date_debut;

    @Column(name = "date_fin")
    private LocalDate date_fin;

    @OneToMany(mappedBy = "analyse", fetch = FetchType.LAZY)
    private List<Teste> testes = new ArrayList<>();

    @ManyToOne
    private Echantillon echantillon;

    @ManyToOne
    private UserLab userLab;

}
