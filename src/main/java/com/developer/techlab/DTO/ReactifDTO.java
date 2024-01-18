package com.developer.techlab.DTO;

import com.developer.techlab.entities.TesteReactif;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReactifDTO {


        private long id;


        private String libelle;


        private int quantite;


        private LocalDate date_exp;

        private String fournisseur;

        private List<TesteReactif> testeReactifs;

    }


