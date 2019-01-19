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
@Table(name = "voters")
public class Voter {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Education education;
    private Integer age;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituencyId")
    private Constituency constituency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(id, voter.id) &&
                gender == voter.gender &&
                education == voter.education &&
                Objects.equals(age, voter.age) &&
                Objects.equals(constituency, voter.constituency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, education, age, constituency);
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id=" + id +
                ", gender=" + gender +
                ", education=" + education +
                ", age=" + age +
                '}';
    }
}