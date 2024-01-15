package com.developer.techlab.entities;


import com.developer.techlab.entities.enums.ResultatTeste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "resultat_table")
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "resultat")
    private ResultatTeste resultat;

    @OneToOne
    private Teste teste;

}
