package com.terranova.api.v1.shared.security.utils;

import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.enums.RoleEnum;
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
public class JwtUtilAdapter implements TokenGeneratorPort {

    private final String jwtSecret;
    private final long jwtExpiration;
    private SecretKey key;

    public JwtUtilAdapter(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.expiration}") long jwtExpiration) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(String identification, List<String> roles){
        List<RoleEnum> rolesEnums = roles.stream().map(RoleEnum::valueOf).toList();
        return Jwts.builder()
                .claim("roles", rolesEnums)
                .setSubject(identification)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key)
                .compact();
    }

//    @Override
//    public Claims extractAllClaims(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    @Override
//    public String getIdentificationFromToken(String token){
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    @Override
//    public void validateJwtToken(String token){
//        try{
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//        }catch (ExpiredJwtException e){
//            throw new TokenExpiredException("Token expired");
//
//        }catch (JwtException e){
//            throw new InvalidJwtTokenException("Invalid Token");
//        }
//    }
}
