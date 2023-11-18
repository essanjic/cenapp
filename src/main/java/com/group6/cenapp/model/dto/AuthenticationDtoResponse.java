package com.group6.cenapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationDtoResponse {
    private final String jwt;
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String city;
    private String role;


}
