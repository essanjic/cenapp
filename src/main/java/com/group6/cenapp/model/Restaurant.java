package com.group6.cenapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="restaurant_id", nullable = false, unique = true)
    private Integer idRestaurant;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    private String phone;
    private String email;
    private String description;
    @Column(length = 50)
    private String shortDescription;
    private String zoneStreet;

    private Double rating;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_user"))
    private User emailUser;
    @ElementCollection
    @CollectionTable(name = "restaurant_day_disponibility", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "day_disponibility")
    private List<String> dayDisponibility;
    private boolean parking;

    private boolean liveMusic;

    private boolean terrace;

    private boolean events;

    private boolean active;

    private String area;

    private Double averageScore;

    private String latitude;

    private String longitude;

    private String cancelationPolicy;

    private String hsePolicy;
    private String sitePolicy;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_restaurant_category"))
    private Category idCategory;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_city"))
    private City idCity;
    private String image;




}
