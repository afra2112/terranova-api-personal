package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.GoogleUserInfo;
import com.terranova.api.v1.auth.domain.model.NameParser;
import com.terranova.api.v1.auth.domain.ports.out.GoogleAuthPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.user.domain.AuthProviderEnum;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoogleLoginUseCase {

    private final GoogleAuthPort googleAuthPort;
    private final UserRepositoryPort userRepositoryPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;

    public GoogleLoginUseCase(GoogleAuthPort googleAuthPort, UserRepositoryPort userRepositoryPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort) {
        this.googleAuthPort = googleAuthPort;
        this.userRepositoryPort = userRepositoryPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    public AuthResponse login(String idToken){

        GoogleUserInfo googleUser = googleAuthPort.verifyToken(idToken);

        NameParser.ParsedName parsedName = NameParser.splitDynamicName(googleUser.fullName());

        User user = userRepositoryPort.findByEmail(googleUser.email()).orElseGet(() ->
                userRepositoryPort.save(new User(
                        null,
                        null,
                        parsedName.getName(),
                        parsedName.getSurname(),
                        googleUser.email(),
                        null,
                        null,
                        null,
                        LocalDateTime.now(),
                        googleUser.picture(),
                        List.of("ROLE_BUYER"),
                        0,
                        new ArrayList<>(),
                        googleUser.googleId(),
                        null,
                        AuthProviderEnum.GOOGLE,
                        true
                ))
        );

        String accessToken = tokenGeneratorPort.generateToken(user.userId(), user.roles());
        String refreshToken = refreshTokenPort.createRefreshToken(user.userId());

        return new AuthResponse(accessToken, refreshToken);
    }
}