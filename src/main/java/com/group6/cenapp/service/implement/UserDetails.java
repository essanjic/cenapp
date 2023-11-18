package com.group6.cenapp.service.implement;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetails {
    org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    String emailUser(String email);
}
