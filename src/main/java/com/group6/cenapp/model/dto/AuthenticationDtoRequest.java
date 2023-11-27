package com.group6.cenapp.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationDtoRequest {
    private String email;
    private String password;

}
