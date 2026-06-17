package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

import java.time.LocalDateTime;

public class VerifyEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public VerifyEmailUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public void verify(String email, String code){

        User user = userRepositoryPort
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new BusinessException(
                                        ErrorCodeEnum.ENTITY_NOT_FOUND
                                )
                        );

        if(user.emailVerified()){
            return;
        }

        if(!code.equals(user.emailVerificationCode())){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_VERIFICATION_CODE
            );
        }

        if(user.emailVerificationExpiresAt().isBefore(LocalDateTime.now())){
            throw new BusinessException(
                    ErrorCodeEnum.VERIFICATION_CODE_EXPIRED, "Expired at: " + user.emailVerificationExpiresAt()
            );
        }

        User updated = user.toBuilder()
                        .emailVerified(true)
                        .emailVerificationCode(null)
                        .emailVerificationExpiresAt(null)
                        .build();

        userRepositoryPort.save(updated);
    }
}