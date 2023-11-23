package com.group6.cenapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, unique = true)
    private Integer category_id;

    @Column(nullable = false, length = 50)
    private String name;

    private String description;
    @Column(name = "short_description", length = 50)
    private String Short_description;
    @Column(name = "food_type", length = 200)
    private String food_type;

    private String image;
}
