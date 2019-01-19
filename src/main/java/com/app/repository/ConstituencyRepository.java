package com.app.repository;

import com.app.model.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
    Optional<Constituency> findById(Long id);
}
