package com.developer.techlab.entities;

import com.developer.techlab.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_lab_table")
public class UserLab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_passe")
    private String mot_passe;

    @Column(name = "role")
    private Role role;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "userLab", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Analyse> analyses = new ArrayList<>();

}
