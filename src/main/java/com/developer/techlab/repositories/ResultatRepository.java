package com.developer.techlab.repositories;

import com.developer.techlab.entities.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {

}
