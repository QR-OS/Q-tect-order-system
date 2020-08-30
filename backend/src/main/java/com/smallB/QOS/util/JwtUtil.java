package com.smallB.QOS.util;

import com.smallB.QOS.error.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Key;


public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String user_id, String user_pw){

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .claim("user_id",user_id)
                .claim("user_pw",user_pw)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isUsable(String token){
        try{
            Object claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claims);
            return true;
        }catch(Exception e){
            System.out.println("isUsable class");
            throw new UnauthorizedException();
        }
    }
}
