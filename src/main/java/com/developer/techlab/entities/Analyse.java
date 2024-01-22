package com.developer.techlab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "analyse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Teste> testes = new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    private Echantillon echantillon;

    @ManyToOne
    private UserLab userLab;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private AnalyseDetails analyseDetails;

}
