package com.group6.cenapp.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id", nullable = false, unique = true)
    private Integer id;
    private Time arrival_time;
    private LocalDate check_in_date;
    private LocalDate checkout_date;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @ManyToOne
    @JoinColumn(name = "user_info")
    private UserInfo user;

}
