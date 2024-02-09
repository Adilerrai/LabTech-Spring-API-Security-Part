package com.developer.techlab.DTO;

import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLabDTO {
        private long id;
        private String nom;
        private String email;
        private String password;
        private Role role;
        @ToString.Exclude
        List<Analyse> analyses;

        public UserLabDTO(long id, String nom, String email, String mot_passe, Role role) {
                this.id = id;
                this.nom = nom;
                this.email = email;
                this.password = mot_passe;
                this.role = role;
        }

        public UserLabDTO(long id) {
                this.id = id;
        }
}

