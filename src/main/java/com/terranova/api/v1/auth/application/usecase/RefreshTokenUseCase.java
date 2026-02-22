package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.RefreshToken;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.domain.ports.out.UserPort;

public class RefreshTokenUseCase {

    private final UserPort userPort;
    private final RefreshTokenPort refreshTokenPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public RefreshTokenUseCase(UserPort userPort, RefreshTokenPort refreshTokenPort, TokenGeneratorPort tokenGeneratorPort) {
        this.userPort = userPort;
        this.refreshTokenPort = refreshTokenPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }

    public AuthenticatedCredentials refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenPort.validateToken(token);

        String newAccessToken = tokenGeneratorPort.generateToken(refreshToken.userIdentification(), userPort.getRolesByIdentification(refreshToken.userIdentification()));
        String newRefreshToken = refreshTokenPort.rotate(refreshToken);

        return new AuthenticatedCredentials(newAccessToken, newRefreshToken);
    }
}
