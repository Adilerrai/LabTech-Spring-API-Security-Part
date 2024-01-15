package com.developer.techlab.repositories;


import com.developer.techlab.entities.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse , Long> {
}
