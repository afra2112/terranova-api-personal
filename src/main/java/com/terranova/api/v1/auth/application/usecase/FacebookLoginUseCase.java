package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.NameParser;
import com.terranova.api.v1.auth.domain.ports.out.FacebookAuthPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook.FacebookUserInfo;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.AuthProviderEnum;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FacebookLoginUseCase {

    private final FacebookAuthPort facebookAuthPort;
    private final UserRepositoryPort userRepositoryPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;

    public FacebookLoginUseCase(FacebookAuthPort facebookAuthPort, UserRepositoryPort userRepositoryPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort) {
        this.facebookAuthPort = facebookAuthPort;
        this.userRepositoryPort = userRepositoryPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    public AuthResponse login(
            String accessToken
    ){
        FacebookUserInfo facebookUser = facebookAuthPort.verifyToken(accessToken);

        NameParser.ParsedName parsedName = NameParser.splitDynamicName(facebookUser.fullName());

        if (facebookUser.email() == null){
            throw new BusinessException(ErrorCodeEnum.FACEBOOK_EMAIL_REQUIRED);
        }

        User user = userRepositoryPort.findByEmail(facebookUser.email())
                .orElseGet(() -> userRepositoryPort.save(new User(
                                        null,
                                        null,
                                        parsedName.getName(),
                                        parsedName.getSurname(),
                                        facebookUser.email(),
                                        null,
                                        null,
                                        null,
                                        LocalDateTime.now(),
                                        facebookUser.picture(),
                                        List.of("ROLE_BUYER"),
                                        0,
                                        new ArrayList<>(),
                                        null,
                                        facebookUser.facebookId(),
                                        AuthProviderEnum.FACEBOOK,
                                        true,
                                        null,
                        null
                                ))
                );

        String jwt = tokenGeneratorPort.generateToken(
                        user.userId(),
                        user.roles()
                );

        String refresh =
                refreshTokenPort.createRefreshToken(
                        user.userId()
                );

        return new AuthResponse(
                jwt,
                refresh
        );
    }
}
