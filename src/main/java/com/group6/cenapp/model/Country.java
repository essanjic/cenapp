package com.group6.cenapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country implements Serializable{
    @Id
    @Column(name="id", nullable = false, unique = true, length = 100)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Getter
    private Long id;
    private String name;
    private String abbreviation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(abbreviation, country.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, abbreviation);
    }
}
