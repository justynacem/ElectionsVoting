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
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String photo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "politicalPartyId")
    private PoliticalParty politicalParty;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituencyId")
    private Constituency constituency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id) &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(surname, candidate.surname) &&
                Objects.equals(age, candidate.age) &&
                Objects.equals(photo, candidate.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, photo);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", photo='" + photo + '\'' +
                '}';
    }
}