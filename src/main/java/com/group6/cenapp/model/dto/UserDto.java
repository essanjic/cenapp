package com.group6.cenapp.model.dto;

import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Role;
import lombok.*;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private City idCity;
    private Role idRole;
    private String idImage;


}