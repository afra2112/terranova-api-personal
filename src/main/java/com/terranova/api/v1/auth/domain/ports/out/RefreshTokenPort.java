package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.RefreshToken;

public interface RefreshTokenPort {

    String createRefreshToken(String identification);

    RefreshToken validateToken(String token);

    void invalidateRefreshToken(String token);

    String rotate(RefreshToken refreshToken);
}
