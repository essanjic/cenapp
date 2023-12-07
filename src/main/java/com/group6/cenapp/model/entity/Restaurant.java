package com.group6.cenapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @BatchSize(size = 50)
    private List<DailyAvailability> day_disponibility;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_restaurant", foreignKey = @ForeignKey(name = "FK_restaurant_category_restaurant"))
    private CategoryRestaurant category_restaurant;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", foreignKey = @ForeignKey(name = "FK_restaurant_food_type"))
    private List<FoodType> foodTypes;
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_city"))
    private Integer city_id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private List<RestaurantTable> restaurant_tables;
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "user_email", nullable = false, foreignKey = @ForeignKey(name = "FK_restaurant_user"))
    private String user_email;
}
