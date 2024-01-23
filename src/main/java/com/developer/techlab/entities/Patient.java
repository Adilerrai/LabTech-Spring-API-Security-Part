package com.developer.techlab.entities;

import com.developer.techlab.entities.enums.Sexe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patient_table")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    @NotNull(message = "Nom cannot be null")
    @Size(min = 1, max = 100, message = "Nom must be between 1 and 100 characters")
    private String nom;

    @Column(name = "email")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "tele")
    @NotNull(message = "Tele cannot be null")
    @Pattern(regexp="(^$|[0-9]{10})", message="Tele should be a valid phone number")
    private String tele;

    @Column(name = "sexe")
    @NotNull(message = "Sexe cannot be null")
    private Sexe sexe;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Echantillon> echantillons = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Analyse> analyses = new ArrayList<>();

    public Patient(long id) {
        this.id = id;
    }
}
