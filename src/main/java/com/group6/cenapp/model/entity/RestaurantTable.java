package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @Column(name = "id_table")
    @JsonIgnore
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_table;
    @Column(name = "cuantity_table")
    private Integer cuantity_table;
    @Column(name = "table_capacity")
    private Integer table_capacity;
    @Column(name = "table_type")
    private String table_type;
}
