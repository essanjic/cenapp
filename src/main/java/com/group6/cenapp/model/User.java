package com.group6.cenapp.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Id
    private Long id;
    @Getter
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Getter
    private String lastName;
    @Column(unique = true, nullable = false)
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;
    @Column(nullable = true, columnDefinition = "TINYINT")
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false, foreignKey = @ForeignKey(name = "FK_user_city"))
    @Getter
    private City idCity;
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "FK_user_role"))
    @Getter
    @Setter
    private Role idRole;
    @OneToOne
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "FK_user_image"))
    @Getter
    private Image image;
}
