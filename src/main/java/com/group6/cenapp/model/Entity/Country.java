package com.group6.cenapp.model.Entity;

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
    @Column(name="country_id", nullable = false, unique = true, length = 100)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idCountry;
    private String name;
    private String abbreviation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(idCountry, country.idCountry) && Objects.equals(name, country.name) && Objects.equals(abbreviation, country.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, name, abbreviation);
    }
}
