package com.group6.cenapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Register Error")

public class RegisterErrorException extends Exception {
    public RegisterErrorException(String message) {
        super(message);
    }
}