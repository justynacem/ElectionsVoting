package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue
    private Long id;
    private String tokenValue;
    private LocalDateTime expirationDate;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voterId")
    private Voter voter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id) &&
                Objects.equals(tokenValue, token.tokenValue) &&
                Objects.equals(expirationDate, token.expirationDate) &&
                Objects.equals(voter, token.voter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tokenValue, expirationDate, voter);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", tokenKey='" + tokenValue + '\'' +
                ", expirationTime=" + expirationDate +
                '}';
    }
}