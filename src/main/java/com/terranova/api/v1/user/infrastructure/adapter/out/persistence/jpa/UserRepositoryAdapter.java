package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import com.terranova.api.v1.user.infrastructure.adapter.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.toDomain(jpaUserRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public User findByIdentification(String identification) {
        return userMapper.toDomain(jpaUserRepository.findByIdentification(identification).orElseThrow(
                ()-> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User not found with identification: " + identification)
        ));
    }

    @Override
    public boolean existsByEmailOrIdentification(String email, String identification) {
        return jpaUserRepository.existsByEmailOrIdentification(email, identification);
    }
}
