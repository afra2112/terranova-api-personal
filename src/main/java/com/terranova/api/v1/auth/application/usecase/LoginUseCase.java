package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.AuthenticatedUser;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.domain.ports.out.AuthenticationPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class LoginUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;

    public LoginUseCase(AuthenticationPort authenticationPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        this.authenticationPort = authenticationPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    public AuthenticatedCredentials login(UserCredential userCredential) throws Exception {

        AuthenticatedUser authenticatedUser = authenticationPort.authenticate(userCredential);

        if (!authenticatedUser.isEmailVerified()){
            throw new BusinessException(ErrorCodeEnum.EMAIL_NOT_VERIFIED, "Please verify your email before logging in");
        }

        String accessToken = tokenGeneratorPort.generateToken(authenticatedUser.userId(), authenticatedUser.roles());
        String refreshToken = refreshTokenPort.createRefreshToken(authenticatedUser.userId());

        return new AuthenticatedCredentials(accessToken, refreshToken);
    }
}
