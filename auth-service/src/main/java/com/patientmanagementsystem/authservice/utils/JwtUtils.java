package com.patientmanagementsystem.authservice.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUtils {

    public final Key secretKey;
    @Value("${JWT_EXPIRATION_TIME}")
    public Long TokenExpiration;

    public JwtUtils(@Value("${JWT_SECRET}") String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TokenExpiration))
                .signWith(secretKey)
                .compact();
    }

    public void TokenValidation(String token) {
     try{
       Jwts.parser().verifyWith((SecretKey) secretKey)
               .build()
               .parseClaimsJws(token);

     }catch (Exception e){
        throw  new JwtException("Invalid Token");
     }
    }
}
