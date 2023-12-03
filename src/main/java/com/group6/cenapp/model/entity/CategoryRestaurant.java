package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_restaurant")
public class CategoryRestaurant {
    @Id
    @JsonIgnore
    @Column(name = "category_restaurant_id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer category_restaurant_id;
    private boolean parking;
    @Column(name = "live_music")
    private boolean live_music;
    private boolean terrace;
    private boolean events;
    private boolean wifi;
}
