package com.terranova.api.v1.auth.infrastructure.config;

import com.terranova.api.v1.auth.application.usecase.*;
import com.terranova.api.v1.auth.domain.ports.out.*;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.application.usecase.FindUserCaseUse;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

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
    public RefreshTokenUseCase refreshTokenUseCase(UserPort userPort, RefreshTokenPort refreshTokenPort, TokenGeneratorPort tokenGeneratorPort){
        return new RefreshTokenUseCase(
                userPort,
                refreshTokenPort,
                tokenGeneratorPort
        );
    }

    @Bean
    public LoginUseCase loginUseCase(AuthenticationPort authenticationPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        return new LoginUseCase(
                authenticationPort,
                tokenGeneratorPort,
                refreshTokenPort
        );
    }

    @Bean
    public LogoutUseCase logoutUseCase(RefreshTokenPort refreshTokenPort){
        return new LogoutUseCase(refreshTokenPort);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(EmailPort emailPort, UserPort userPort){
        return new RegisterUserUseCase(userPort, emailPort);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GoogleLoginUseCase googleLoginUseCase(GoogleAuthPort googleAuthPort, UserRepositoryPort userRepositoryPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        return new GoogleLoginUseCase(googleAuthPort, userRepositoryPort, tokenGeneratorPort, refreshTokenPort);
    }

    @Bean LinkGoogleAccountUseCase linkGoogleAccountUseCase(GoogleAuthPort googleAuthPort, AuthFacade authFacade, UserRepositoryPort userRepositoryPort){
        return new LinkGoogleAccountUseCase(googleAuthPort, userRepositoryPort, authFacade);
    }

    @Bean FacebookLoginUseCase facebookLoginUseCase(FacebookAuthPort facebookAuthPort, UserRepositoryPort userRepositoryPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        return new FacebookLoginUseCase(facebookAuthPort, userRepositoryPort, tokenGeneratorPort, refreshTokenPort);
    }

    @Bean LinkFacebookAccountUseCase linkFacebookAccountUseCase(FacebookAuthPort facebookAuthPort, UserRepositoryPort userRepositoryPort, AuthFacade authFacade){
        return new LinkFacebookAccountUseCase(facebookAuthPort, userRepositoryPort, authFacade);
    }

    @Bean VerifyEmailUseCase verifyEmailUseCase(UserRepositoryPort userRepositoryPort){
        return new VerifyEmailUseCase(userRepositoryPort);
    }

    @Bean ResendVerificationUseCase resendVerificationUseCase(UserRepositoryPort userRepositoryPort, EmailPort emailPort){
        return new ResendVerificationUseCase(userRepositoryPort, emailPort);
    }

    @Bean RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
