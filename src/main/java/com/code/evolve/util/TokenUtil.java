package com.code.evolve.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtil {

    private final String key="my-secret-key-for-jwt-generation-testing-1234";

    public String generateToken(){
         return Jwts.builder()
                 .setSubject("Yugesh")
                 .signWith(getKeys(), SignatureAlgorithm.HS256)
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))
                 .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(getKeys())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKeys() {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
}
