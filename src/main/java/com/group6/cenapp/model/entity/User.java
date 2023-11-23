package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private int idUser;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false, foreignKey = @ForeignKey(name = "FK_user_city"))
    @JsonIgnore
    private City idCity;
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "FK_user_role"))
    @JsonIgnore
    private Role idRole;
    private String image;

    public int getId() {
        return idUser;
    }

    public Role getRole() {
        return idRole;
    }
}
