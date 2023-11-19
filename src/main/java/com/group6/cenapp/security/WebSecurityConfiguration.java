package com.group6.cenapp.security;

import com.group6.cenapp.security.jwt.JwtEntryPoint;
import com.group6.cenapp.security.jwt.JwtRequestFilter;
import com.group6.cenapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserService userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;


    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers( "/v2/api-docs", "/swagger-ui/**",
                            "/swagger-resources/**").permitAll()
                    .antMatchers("/authenticate").permitAll()
                    .antMatchers("/users/create").permitAll()
                    /* USER */
                    .antMatchers("/reservations/create").hasAuthority("USER")
                    /* ADMIN */
                    .antMatchers("/cities/create", "/cities/update", "/cities/delete/{id}").hasAuthority("ADMIN")
                    .antMatchers("/restaurants/create", "/products/update", "/products/delete/{id}").hasAuthority("ADMIN")
                    .antMatchers("/attributes/**").hasAuthority("ADMIN")
                    .antMatchers("/images/**").hasAuthority("ADMIN")


                    .anyRequest().permitAll()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .formLogin()
                    .and()
                    .logout();

        }

}