package com.group6.cenapp.model;


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
public class City implements Serializable {
    @Id
    @Column(name="id", nullable = false, unique = true, length = 100)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "FK_city_country"))
    private Country idCountry;
    @Column(nullable = false, length = 100)
    @Getter
    private String name;
    private String abbreviation;




}
