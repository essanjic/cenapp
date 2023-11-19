package com.group6.cenapp.security.jwt;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = Logger.getLogger(JwtEntryPoint.class);


    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException, jakarta.servlet.ServletException {
        logger.error("Fallo en el metodo commence");
        response.sendError(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado");
    }
}