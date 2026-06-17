package com.terranova.api.v1.auth.infrastructure.adapter.out.user;

import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.UserPort;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.application.usecase.FindUserCaseUse;
import com.terranova.api.v1.user.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserRegistryAdapter implements UserPort {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserCaseUse findUserCaseUse;
    private final AuthMapper authMapper;

    @Override
    public boolean existByEmailOrIdentification(String email, String identification) {
        return findUserCaseUse.existsEmailOrIdentification(email, identification);
    }

    @Override
    @Transactional
    public User createUser(NewUserDomain newUserDomain) {
        return  createUserUseCase.createUser(authMapper.fromUserAuthDomainToUserDomain(newUserDomain));
    }

    @Override
    public List<String> getRolesByIdentification(UUID userId) {
        return findUserCaseUse.findUserByIdentification(userId).roles();
    }
}
