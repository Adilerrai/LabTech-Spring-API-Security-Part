package com.developer.techlab.entities;

import com.developer.techlab.entities.enums.StatutEchantillon;
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
@Table(name = "echantillon_table")
public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_prev")
    private LocalDate date_prev;

    @Column(name = "statut")
    private StatutEchantillon statut;

    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "echantillon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Analyse> analyses = new ArrayList<>();

}
