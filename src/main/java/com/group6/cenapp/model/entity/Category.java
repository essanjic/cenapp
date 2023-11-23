package com.group6.cenapp.model.entity;

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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, unique = true)
    private Integer idCategory;

    @Column(nullable = false, length = 50)
    private String name;

    private String description;
    @Column(name = "short_description", length = 50)
    private String ShortDescription;
    @Column(name = "food_type", length = 200)
    private String foodType;

    private String image;
}
