package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.RefreshToken;

import java.util.UUID;

public interface RefreshTokenPort {

    String createRefreshToken(UUID userId);

    RefreshToken validateToken(String token);

    void invalidateRefreshToken(String token);

    String rotate(RefreshToken refreshToken);
}
