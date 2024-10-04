package com.Ayush.Cryptik.service;

import java.util.Date;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(String username);
    Date extractExpiDate(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    boolean validateToken(String token, UserDetails userDetails);
}