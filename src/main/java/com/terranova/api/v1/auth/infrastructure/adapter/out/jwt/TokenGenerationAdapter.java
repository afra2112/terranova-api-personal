package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.shared.security.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TokenGenerationAdapter implements TokenGeneratorPort {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(UUID userId, List<String> roles) {
        return jwtUtil.generateToken(userId, roles);
    }
}
