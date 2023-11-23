package com.group6.cenapp.controller;

import com.group6.cenapp.model.Entity.City;
import com.group6.cenapp.model.Entity.Role;
import com.group6.cenapp.model.Entity.User;
import com.group6.cenapp.security.jwt.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Authentication")
@CrossOrigin("*")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDtoRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthenticationServiceException("Incorrect or invalid credentials", e);
        }

        User user = userServiceImpl.findByEmail(authenticationRequest.getEmail());
        if (user != null) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            final int id = userServiceImpl.getUserById(user.getId()).get().getId();
            final String name = user.getName();
            final String lastName = user.getLastName();
            final String email = user.getEmail();
            final City city = user.getIdCity();
            final Role role = user.getIdRole();

            return ResponseEntity.ok(new AuthenticationDtoResponse((jwt), id, name, lastName, email, city, role));
        } else {
            throw new AuthenticationServiceException("User not found");
        }
    }

    @RequestMapping("/cenapp")
    public String hello() {
        return "Welcome to cenapp";
    }
}