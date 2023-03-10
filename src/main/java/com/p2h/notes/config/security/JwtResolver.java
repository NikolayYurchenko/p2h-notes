package com.p2h.notes.config.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtResolver {

    @Value("${notes-manager.base.settings.jwt-time-expiration}")
    private int expirationTime;

    @Value("${notes-manager.base.settings.jwt-secret}")
    private String jwtSecret;

    /**
     * Init secret key
     */
    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String generateToken(String userUid) {

        Claims claims = Jwts.claims().setSubject(userUid);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public void validateToken(String token) {

        try {

           Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

        } catch (JwtException | IllegalArgumentException e) {

            throw new RuntimeException("Expired or invalid JWT token");
        }
    }

    @SneakyThrows
    public Jws<Claims> parseClaims(String token) {

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    }
}
