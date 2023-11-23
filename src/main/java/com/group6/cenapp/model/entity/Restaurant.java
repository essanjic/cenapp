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
    private Integer id_restaurant;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    private String phone;
    private String description;
    @Column(name ="short_description" ,length = 50)
    private String short_description;
    @Column(name = "zone_street")
    private String zone_street;

    private Double rating;

    @Column(name = "email_restaurant")
    private String email;
    @ElementCollection
    @CollectionTable(name = "restaurant_day_disponibility", joinColumns = @JoinColumn(name = "restaurant_id"))
    @JsonProperty("day_disponibility")
    private List<String> day_disponibility;
    private boolean parking;
    @Column(name = "live_music")
    private boolean live_music;

    private boolean terrace;

    private boolean events;

    private boolean active;

    private String area;
    @Column(name = "average_score")
    private Double average_score;

    private String latitude;

    private String longitude;
    @Column(name = "cancellation_policy")
    private String cancellation_policy;
    @Column(name = "hse_policy")
    private String hse_policy;
    @Column(name = "site_policy")
    private String site_policy;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_restaurant_category"))
    private Category category;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_city"))
    private City idCity;

    @Column(name = "image")
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
