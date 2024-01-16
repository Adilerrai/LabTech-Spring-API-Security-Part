package com.developer.techlab.repositories;


import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.enums.StatutEchantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse , Long> {
}
