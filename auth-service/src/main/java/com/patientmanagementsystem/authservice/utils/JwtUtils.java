package com.patientmanagementsystem.authservice.utils;

import com.patientmanagementsystem.authservice.Dto.types.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtUtils {

    public final Key secretKey;
    @Value("${JWT_EXPIRATION_TIME}")
    public Long TokenExpiration;

    public JwtUtils(@Value("${JWT_SECRET}") String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role,Long currentUserId) {
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .claim("id",currentUserId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TokenExpiration))
                .signWith(secretKey)
                .compact();
    }



    public Claims getClaimsFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public TokenResponse getEmailAndRoleFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        log.info("All JWT claims: {}", claims);

        String email = claims.getSubject();
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        log.info("Email from JWT: {}", email);
        log.info("Role from JWT: {}", role);

        return new TokenResponse(email, role,id);
    }
}
