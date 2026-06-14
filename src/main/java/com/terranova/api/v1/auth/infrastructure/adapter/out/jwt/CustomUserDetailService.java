package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.model.CustomUserDetails;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User not found with email: " + email));

        return new CustomUserDetails(user);
    }
}
