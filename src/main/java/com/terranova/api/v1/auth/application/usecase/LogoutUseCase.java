package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;

public class LogoutUseCase {

    private final RefreshTokenPort refreshTokenPort;

    public LogoutUseCase(RefreshTokenPort refreshTokenPort) {
        this.refreshTokenPort = refreshTokenPort;
    }

    public void logout(String token) {
        if (token == null){
            throw new RuntimeException("The given refresh token is null."); //TODO: implement correct business exception
        }
        refreshTokenPort.invalidateRefreshToken(token);
    }
}
