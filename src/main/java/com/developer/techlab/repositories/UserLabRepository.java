package com.developer.techlab.repositories;

import com.developer.techlab.entities.UserLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLabRepository extends JpaRepository<UserLab, Long> {

    List<UserLab> findByDeletedFalse();
    Optional<UserLab> findByIdAndDeletedFalse(Long id);
    Optional<UserLab> findByNomAndDeletedFalse(String name);
    Optional<UserLab> findByEmailAndDeletedFalse(String email);
}
