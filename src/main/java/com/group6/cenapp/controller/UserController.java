package com.group6.cenapp.controller;

import com.group6.cenapp.exception.RegisterErrorException;
import com.group6.cenapp.model.entity.AuthRequest;
import com.group6.cenapp.model.entity.UserInfo;
import com.group6.cenapp.service.JwtService;
import com.group6.cenapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) throws RegisterErrorException {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticateUser(authRequest);

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getEmail());
            Map<String, String> response = createTokenResponse(token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña inválidos");
        }
    }



    @GetMapping("/authenticate")
    private Authentication authenticateUser(@RequestBody AuthRequest authRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
    }

    private Map<String, String> createTokenResponse(String token) {
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<String> checkEmailAvailability(@PathVariable String email) {
        boolean isAvailable = service.isEmailAvailable(email);

        if (!isAvailable) {
            return new ResponseEntity<>("Email is available", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email is not available", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/get-user/{token}")
    public ResponseEntity<UserInfo> getUserInfoFromToken(@PathVariable String token) {

        System.out.println(token);
        try {
            String username = jwtService.extractUsername(token);
            System.out.println(username);
            if(!username.isEmpty()){
                System.out.println(username);
                UserInfo userInfo = service.getUserInfo(username);
                return new ResponseEntity<>(userInfo,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}