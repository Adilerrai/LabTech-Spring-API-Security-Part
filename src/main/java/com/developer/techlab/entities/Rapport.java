package com.developer.techlab.entities;

import com.developer.techlab.entities.enums.PeriodeRapport;
import lombok.Getter;
import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="rapports_table")
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type")
    private String type;

    @Column(name="periode")
    private PeriodeRapport periode;

    @Column(name="donnee")
    private String donnee;

    @Column(name="graphique")
    private String graphique;

}
