package com.smallB.QOS.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String user_id, String user_pw){

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .claim("user_id",user_id)
                .claim("user_pw",user_pw)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }
}
