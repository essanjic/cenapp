package com.group6.cenapp.security;

import com.group6.cenapp.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

        @Override
        protected void configure(HttpSecurity http ) throws Exception{
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers( "/v2/api-docs", "/swagger-ui/**",
                        "/swagger-resources/**").permitAll()
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers("/users/create").permitAll()
                /* USER */
                .requestMatchers("/reservations/create").hasAuthority("USER")
                /* ADMIN */
                .requestMatchers("/cities/create", "/cities/update", "/cities/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/products/create", "/products/update", "/products/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/attributes/**").hasAuthority("ADMIN")
                .requestMatchers("/images/**").hasAuthority("ADMIN")
                //.antMatchers("/products/**", "/cities/**", "/categories").permitAll()

                .anyRequest().permitAll();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}