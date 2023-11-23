package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String description;
    @Column(name ="short_description" ,length = 50)
    private String shortDescription;
    @Column(name = "zone/street")
    private String zoneStreet;

    private Double rating;

    @Column(name = "email_restaurant")
    private String email;
    @ElementCollection
    @CollectionTable(name = "restaurant_day_disponibility", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "day_disponibility")
    private List<String> dayDisponibility;
    private boolean parking;
    @Column(name = "live_music")
    private boolean liveMusic;

    private boolean terrace;

    private boolean events;

    private boolean active;

    private String area;
    @Column(name = "average_score")
    private Double averageScore;

    private String latitude;

    private String longitude;
    @Column(name = "cancelation_policy")
    private String cancelationPolicy;
    @Column(name = "hse_policy")
    private String hsePolicy;
    @Column(name = "site_policy")
    private String sitePolicy;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_restaurant_category"))
    private Category idCategory;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_city"))
    private City idCity;
    private String image;

    @Transient
    @JsonProperty("city_id")
    private Integer cityId;

    @PrePersist
    public void prePersist() {
        if (cityId != null) {
            idCity = new City();
            idCity.setIdCity(cityId);
        }
    }
}
