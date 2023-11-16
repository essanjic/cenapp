package com.group6.cenapp.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String SECRET_KEY = "secret";

    public String extractUserName(String token) {
        return extractClaimUsername(token);
    }

    public Class<? extends Module> extractExpiration(String token) {
        return extractClaimDate(token);
    }

    public Class<? extends Module> extractClaimDate(String token){
        Module claims = extractAllClaims(token);
        return claims.getClass();
    }

    public String extractClaimUsername(String token){
        Module claims = extractAllClaims(token);
        return claims.getName();
    }

    private Module extractAllClaims(String token) {
       // System.out.println("extractAllClaims" + Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody());
        return Jwts.parser().setSigningKey(SECRET_KEY).getClass().getModule();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        // System.out.println(new Date(now.getTime() + 10000 * 60 * 1000));
        // System.out.println(Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())));
        String compact = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(now.getTime() + 100 * 60 * 1000))// 100 minutes
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 +60 * 10))
                //sugiereme una librería no deprecada para la línea de abajo
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return compact;

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        // System.out.println("isTokenExpired" + extractExpiration(token).before(new Date()));
        extractExpiration(token);
        return false;
    }
}