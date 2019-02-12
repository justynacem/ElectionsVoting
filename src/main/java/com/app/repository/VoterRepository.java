package com.app.repository;

import com.app.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    @Query("SELECT AVG(age) from Voter")
    double getAverageAge();
}
