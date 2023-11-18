package com.group6.cenapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long id;
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
    private User idUser;
    @ElementCollection
    @CollectionTable(name = "restaurant_day_disponibility", joinColumns = @JoinColumn(name = "restaurant_id"))
    @MapKeyColumn(name = "day_of_week")
    @AttributeOverride(name = "openHour", column = @Column(name = "open_hour"))
    @AttributeOverride(name = "closeHour", column = @Column(name = "close_hour"))
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, DailyAvailability> dayDisponibility;
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
    private Category category;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_city"))
    private City city;
    @ManyToMany
    @JoinTable(name = "restaurant_image", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> image;

}
