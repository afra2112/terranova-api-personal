package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.shared.security.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TokenGenerationAdapter implements TokenGeneratorPort {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(String identification, List<String> roles) {
        return jwtUtil.generateToken(identification, roles);
    }
}
