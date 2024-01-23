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
@Table(name = "analyse_details_table")
public class AnalyseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "analyseDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TesteDetails> testeDetails = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "analyseDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Analyse> analyses = new ArrayList<>();

}
