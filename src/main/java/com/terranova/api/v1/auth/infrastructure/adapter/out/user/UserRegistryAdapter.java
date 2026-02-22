package com.terranova.api.v1.auth.infrastructure.adapter.out.user;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.domain.ports.out.UserRegisterPort;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.application.usecase.FindUserCaseUse;
import com.terranova.api.v1.user.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class UserRegistryAdapter implements UserRegisterPort {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserCaseUse findUserCaseUse;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;
    private final AuthMapper authMapper;

    @Override
    public boolean existByEmailOrIdentification(String email, String identification) {
        return findUserCaseUse.existsEmailOrIdentification(email, identification);
    }

    @Override
    @Transactional
    public AuthenticatedCredentials createUser(NewUserDomain newUserDomain) {
        User userDomain = createUserUseCase.createUser(authMapper.fromUserAuthDomainToUserDomain(newUserDomain));

        String accessToken = tokenGeneratorPort.generateToken(userDomain.identification(), List.of("ROLE_BUYER"));
        String refreshToken = refreshTokenPort.createRefreshToken(userDomain.identification());

        return new AuthenticatedCredentials(accessToken, refreshToken);
    }
}
