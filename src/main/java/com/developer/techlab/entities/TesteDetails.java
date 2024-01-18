package com.developer.techlab.entities;


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
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "testeDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Teste> testes = new ArrayList<>();
}
