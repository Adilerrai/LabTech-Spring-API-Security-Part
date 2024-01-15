package com.developer.techlab.repositories;

import com.developer.techlab.entities.Reactif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactifRepository extends JpaRepository<Reactif, Long> {
}
