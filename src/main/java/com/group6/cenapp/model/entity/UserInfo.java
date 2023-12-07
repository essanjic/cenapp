package com.group6.cenapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "last_name")
    private String last_name;
    @Column(unique = true)
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String roles;
    @JoinColumn(name = "id_city", nullable = false, foreignKey = @ForeignKey(name = "FK_user_city"))
    private Integer id_city;
    private String image;

    public UserInfo(Integer id) {
        this.id = id;
    }
}