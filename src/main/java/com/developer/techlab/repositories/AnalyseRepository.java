package com.developer.techlab.repositories;


import com.developer.techlab.entities.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse , Long> {
    @Query("SELECT a FROM Analyse a WHERE a.date_debut <= :currentDate AND (a.date_fin IS NULL OR a.date_fin >= :currentDate)")
    List<Analyse> findOngoingAnalyses(@Param("currentDate") LocalDate currentDate);
}
