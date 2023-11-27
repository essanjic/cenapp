package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City implements Serializable {
    @Id
    @Column(name = "city_id", nullable = false, unique = true, length = 100)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idCity;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "FK_city_country"))
    @JsonIgnore
    private Country idCountry;
    @Column(nullable = false, length = 100)
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String abbreviation;

}
