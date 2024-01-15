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
@Table(name = "analyse_details_table")
public class AnalyseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "analyseDetails")
    private List<TesteDetails> testeDetails = new ArrayList<>();

}
