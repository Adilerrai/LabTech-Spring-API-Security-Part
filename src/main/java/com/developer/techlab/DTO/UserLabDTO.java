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
        private String email;
        private String mot_passe;
        private Role role;
        @ToString.Exclude
        List<Analyse> analyses;
    }

