package com.developer.techlab.repositories;

import com.developer.techlab.entities.Echantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchantillonRepository extends JpaRepository<Echantillon, Long> {
}
