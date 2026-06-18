package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.ports.out.EmailPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class ResendVerificationUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final EmailPort emailPort;

    public ResendVerificationUseCase(UserRepositoryPort userRepositoryPort, EmailPort emailPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.emailPort = emailPort;
    }

    public void resend(String email){

        User user = userRepositoryPort.findByEmail(email).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User not found by email: " + email)
        );

        if (user.emailVerified()){
            return;
        }

        String code = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1000000));

        User updated = user.toBuilder()
                .emailVerificationCode(code)
                .emailVerificationExpiresAt(LocalDateTime.now().plusMinutes(15))
                .build();

        userRepositoryPort.save(updated);

        emailPort.sendVerificationCode(updated.email(), code);
    }
}
