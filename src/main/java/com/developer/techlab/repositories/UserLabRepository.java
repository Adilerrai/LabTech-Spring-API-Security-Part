package com.developer.techlab.repositories;

import com.developer.techlab.entities.UserLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLabRepository extends JpaRepository<UserLab, Long> {
}
