package com.group6.cenapp.controller;

import com.group6.cenapp.model.User;
import com.group6.cenapp.model.dto.AuthenticationDtoRequest;
import com.group6.cenapp.model.dto.AuthenticationDtoResponse;
import com.group6.cenapp.security.jwt.JwtUtil;
import com.group6.cenapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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
    private UserService userServiceImpl;


    @RequestMapping(value = "/v1/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDtoRequest authenticationRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new AuthenticationException("Incorrect username or password") {};
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken((User) userDetails);
        final int id = userServiceImpl.idUser(authenticationRequest.getEmail());
        final String name = userServiceImpl.nameUser(authenticationRequest.getEmail());
        final String lastName = userServiceImpl.lastNameUser(authenticationRequest.getEmail());
        final String email = userServiceImpl.emailUser(authenticationRequest.getEmail());
        final String city = userServiceImpl.cityUser(authenticationRequest.getEmail());
        final String role = userServiceImpl.roleUser(authenticationRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationDtoResponse((jwt), id, name, lastName, email, city, role));
    }

    @RequestMapping({"/v1/api/cenapp"})
    public String hello() {
        return "Welcome to cenapp";
    }
}
