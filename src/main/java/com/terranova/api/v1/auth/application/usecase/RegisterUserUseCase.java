package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.EmailPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.domain.ports.out.UserPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class RegisterUserUseCase {

    private final UserPort userPort;
    private final EmailPort emailPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;

    public RegisterUserUseCase(UserPort userPort, EmailPort emailPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort) {
        this.userPort = userPort;
        this.emailPort = emailPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    public AuthenticatedCredentials createUser(NewUserDomain newUserDomain){
        if (userPort.existByEmailOrIdentification(newUserDomain.email(), newUserDomain.identification())){
            throw new BusinessException(ErrorCodeEnum.USER_ALREADY_EXISTS, "User with email: " + newUserDomain.email() + ". Or Identification: " + newUserDomain.identification() + ". Already exists, please sign in.");
        }

        validateBirthDate(newUserDomain.birthday());

        User saved = userPort.createUser(newUserDomain);

        emailPort.sendVerificationCode(saved.email(), saved.emailVerificationCode());

        String accessToken = tokenGeneratorPort.generateToken(saved.userId(), List.of("ROLE_BUYER"));
        String refreshToken = refreshTokenPort.createRefreshToken(saved.userId());

        return new AuthenticatedCredentials(accessToken, refreshToken);
    }

    private void validateBirthDate(LocalDate date){
        int age = Period.between(date, LocalDate.now()).getYears();

        if (age < 18){
            throw new BusinessException(ErrorCodeEnum.INVALID_BIRTH_DATE);
        }
    }
}
