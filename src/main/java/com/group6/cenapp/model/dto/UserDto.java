package com.group6.cenapp.model.dto;

import com.group6.cenapp.model.Entity.City;
import com.group6.cenapp.model.Entity.Role;
import com.group6.cenapp.model.Entity.User;
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


    public void setRole(Role roleUser) {
    }

    public void setPassword(String encode) {
    }

    public CharSequence getPassword() {
        return null;
    }

    public User getRole() {
        return null;
    }
}