package com.developer.techlab.repositories;

import com.developer.techlab.entities.TesteReactif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteReactifRepository extends JpaRepository<TesteReactif, Long> {
}
