package com.group6.cenapp.model.dto;

import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Role;
import lombok.*;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private City city;
    private Role roleId;
    private String image;


}