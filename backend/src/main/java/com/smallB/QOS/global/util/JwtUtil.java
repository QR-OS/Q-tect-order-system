package com.smallB.QOS.global.util;

import com.smallB.QOS.user.domain.SessionRequestDto;
import com.smallB.QOS.user.error.Exception.TokenExpiredException;
import com.smallB.QOS.user.error.Exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(SessionRequestDto sessionRequestDto){

        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Date now = new Date();
        return Jwts.builder()
                .claim("user_id",sessionRequestDto.getUser_id())
                .setExpiration(new Date(now.getTime()+3*60*60*1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isUsable(String token){
        try{
            isTokenExpired(token);
            return true;
        }catch(Exception e){
            throw new TokenExpiredException();
        }
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
