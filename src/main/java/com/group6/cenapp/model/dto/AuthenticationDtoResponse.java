package com.group6.cenapp.model.dto;

import com.group6.cenapp.model.Entity.City;
import com.group6.cenapp.model.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationDtoResponse {
    private final String jwt;
    private int id;
    private String name;
    private String lastName;
    private String email;
    private City city;
    private Role role;
}