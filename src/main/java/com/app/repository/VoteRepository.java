package com.app.repository;

import com.app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("select v from Vote v order by v.votes desc")
    List<Vote> findMaxVotes();
}