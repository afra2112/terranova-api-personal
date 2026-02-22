package com.terranova.api.v1.auth.infrastructure.config;

import com.terranova.api.v1.auth.application.usecase.LoginUseCase;
import com.terranova.api.v1.auth.application.usecase.RegisterUserUseCase;
import com.terranova.api.v1.auth.domain.ports.out.AuthenticationPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.auth.domain.ports.out.UserRegisterPort;
import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.application.usecase.FindUserCaseUse;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthDomainConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserCaseUse findUserCaseUse(UserRepositoryPort userRepositoryPort){
        return new FindUserCaseUse(userRepositoryPort);
    }

    @Bean
    public LoginUseCase loginUseCase(TokenGeneratorPort tokenGeneratorPort, AuthenticationPort authenticationPort, RefreshTokenPort refreshTokenPort){
        return new LoginUseCase(
                authenticationPort,
                tokenGeneratorPort,
                refreshTokenPort
        );
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRegisterPort userRegisterPort){
        return new RegisterUserUseCase(userRegisterPort);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
