package com.group6.cenapp.security;

import com.group6.cenapp.security.jwt.JwtRequestFilter;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    public static class SecurityRoles {
        public static final String USER = "USER";
        public static final String ADMINISTRATOR = "ADMIN";
        public static final String ADMIN = "COLABORATOR";
    }

    @Bean
    public JwtRequestFilter autenticationJwtRequestFilter() {
        return new JwtRequestFilter();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authentication -> authentication;
    }

    @Bean
    public SecurityConfigurer securityConfigurer() {
        return new SecurityConfigurer();
    }

    private static class SecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) {
            configureCors(http);
            configureSessionManagement(http);

            try {
                http.authorizeRequests()
                        .anyRequest(
                        ).permitAll()
                        .anyRequest().permitAll()
                        .anyRequest().hasAuthority(SecurityRoles.USER)
                        .anyRequest(
                        ).hasAuthority(SecurityRoles.ADMINISTRATOR)
                        .anyRequest().hasAuthority(SecurityRoles.ADMINISTRATOR)
                        .anyRequest().hasAuthority(SecurityRoles.ADMIN)
                        .anyRequest().permitAll();
            } catch (Exception e) {
                throw new RuntimeException("Error configuring security", e);
            }
        }

        private void configureCors(HttpSecurity http) {
            try {
                //copilot, help me in this line under because cors are deprecated
                HttpSession httpSession = null;
            } catch (Exception e) {
                throw new RuntimeException("Error configuring CORS", e);
            }
        }

        private void configureSessionManagement(HttpSecurity http) {
            try {
                SessionCreationPolicy sessionCreationPolicy = SessionCreationPolicy.STATELESS;
            } catch (Exception e) {
                throw new RuntimeException("Error configuring session management", e);
            }
        }
    }
}