package com.group6.cenapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
    private int id;
    private String email;
    private String roles;
    private String name;
    private String last_name;
    private String image;
}