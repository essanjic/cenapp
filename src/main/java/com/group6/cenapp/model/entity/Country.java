package com.group6.cenapp.model.entity;

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
@Table(name = "country")
public class Country implements Serializable{
    @Id
    @Column(name="country_id", nullable = false, unique = true, length = 100)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_country;
    private String name;
    private String abbreviation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(id_country, country.id_country) && Objects.equals(name, country.name) && Objects.equals(abbreviation, country.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_country, name, abbreviation);
    }
}
