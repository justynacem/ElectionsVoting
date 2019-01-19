package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue
    private Long id;
    private Integer votes;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) &&
                Objects.equals(votes, vote.votes) &&
                Objects.equals(candidate, vote.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, votes, candidate);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", votesCounter=" + votes +
                '}';
    }
}