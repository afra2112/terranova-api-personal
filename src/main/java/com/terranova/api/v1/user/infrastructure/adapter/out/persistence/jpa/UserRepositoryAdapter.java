package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.user.domain.model.SellerSummary;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import com.terranova.api.v1.user.infrastructure.adapter.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public User findById(UUID userId) {
        return userMapper.toDomain(jpaUserRepository.findByUserId(userId).orElseThrow(
                ()-> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User not found with identification: " + userId)
        ));
    }

    @Override
    public Optional<User> findByVerificationToken(String verificationToken) {
        return Optional.of(userMapper.toDomain(jpaUserRepository.findByEmailVerificationCode(verificationToken)));
    }

    @Override
    public Optional<User> findByGoogleId(String googleId) {
        return Optional.ofNullable(jpaUserRepository.findByGoogleId(googleId)).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByFacebookId(String facebookId) {
        return Optional.ofNullable(jpaUserRepository.findByFacebookId(facebookId)).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jpaUserRepository.findByEmail(email)).map(userMapper::toDomain);
    }

    @Override
    public List<SellerSummary> findBatchUsers(List<UUID> ids) {
        return jpaUserRepository.findSellerSummaryByIds(ids);
    }

    @Override
    public boolean existsByEmailOrIdentification(String email, String identification) {
        return jpaUserRepository.existsByEmailOrIdentification(email, identification);
    }
}
