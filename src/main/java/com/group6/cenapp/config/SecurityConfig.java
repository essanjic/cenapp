package com.group6.cenapp.config;

import com.group6.cenapp.filter.JwtAuthFilter;
import com.group6.cenapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/api/restaurants/create",
                        "/v1/api/restaurants/pages",
                        "/v1/api/restaurants",
                        "v1/api/restaurants/{id}",
                        "/v1/api/restaurants/delete/{id}",
                        "/v1/api/restaurants/update",
                        "/v1/api/foodtypes",
                        "/auth/check-jwt",
                        "/auth/get-user/*",
                        "/auth/check-email/*",
                        "/v1/api/foodtypes/{id}",
                        "/v1/api/cities",
                        "/v1/api/cities/{id}",
                        "/v1/api/countries",
                        "/v1/api/countries/{id_country}/cities",
                        "/v1/api/restaurants/category/{id}",
                        "/auth/toggle-fav/{userId}/{restId}",
                        "auth/get-fav/{userId}")
                .permitAll()
                .requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/admin/**",
                        "/v1/api/foodtypes/create",
                        "/v1/api/foodtypes/update",
                        "/v1/api/reservations").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}