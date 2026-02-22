package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.model.RefreshToken;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.jparepository.JpaRefreshTokenRepository;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Component
public class RefreshTokenAdapter implements RefreshTokenPort {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final AuthMapper authMapper;

    @Override
    public String createRefreshToken(String userIdentification){
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setToken(UUID.randomUUID().toString());
        token.setUserIdentification(userIdentification);
        token.setExpiresAt(LocalDateTime.now().plusDays(30));

        jpaRefreshTokenRepository.save(token);

        return token.getToken();
    }

    @Override
    @Transactional
    public void invalidateRefreshToken(String token) {
        jpaRefreshTokenRepository.deleteByToken(token);
    }

    @Override
    public RefreshToken validateToken(String token) {
        RefreshTokenEntity refreshTokenEntity = jpaRefreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.INVALID_TOKEN));

        if (refreshTokenEntity.isExpired() || refreshTokenEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_EXPIRED);
        }

        return authMapper.fromRefreshTokenEntityToRefreshToken(refreshTokenEntity);
    }

    @Override
    public String rotate(RefreshToken refreshToken) {
        jpaRefreshTokenRepository.delete(authMapper.fromRefreshTokenToRefreshTokenEntity(refreshToken));
        return createRefreshToken(refreshToken.userIdentification());
    }
}
