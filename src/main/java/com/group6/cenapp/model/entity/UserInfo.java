package com.group6.cenapp.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "last_name")
    private String last_name;
    @Column(unique = true)
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String roles;
    @ElementCollection
    @Nullable
    private List<Integer> favourites = new ArrayList<>();
    @JoinColumn(name = "id_city", nullable = false, foreignKey = @ForeignKey(name = "FK_user_city"))
    private Integer id_city;
    private String image;
    @Nullable
    private Integer id_country;
}