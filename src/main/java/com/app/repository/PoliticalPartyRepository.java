package com.app.repository;

import com.app.model.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
}
