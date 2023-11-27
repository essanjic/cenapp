package com.group6.cenapp.model.dto;

import com.group6.cenapp.model.entity.City;
import com.group6.cenapp.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




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