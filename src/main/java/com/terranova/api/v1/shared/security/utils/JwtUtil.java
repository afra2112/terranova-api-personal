package com.terranova.api.v1.shared.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final String jwtSecret;
    private final long jwtExpiration;
    private SecretKey key;

    public JwtUtil(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.expiration}") long jwtExpiration) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String identification, List<String> roles){
        return Jwts.builder()
                .claim("roles", roles)
                .setSubject(identification)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getIdentificationFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateJwtToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        }catch (ExpiredJwtException e){
            throw new RuntimeException("Token expired"); //TODO: implement business exception

        }catch (JwtException e){
            throw new RuntimeException("Invalid Token"); //TODO: implement business exception
        }
    }
}
