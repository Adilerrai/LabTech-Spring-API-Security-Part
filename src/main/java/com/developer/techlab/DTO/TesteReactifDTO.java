package com.developer.techlab.DTO;

import com.developer.techlab.entities.Reactif;
import com.developer.techlab.entities.Teste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TesteReactifDTO {


    private long id;
    private int quantite;
    private Teste teste;
    private Reactif reactif;

}
