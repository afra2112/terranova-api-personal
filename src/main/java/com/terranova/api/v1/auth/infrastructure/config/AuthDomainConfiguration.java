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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
    public RegisterUserUseCase registerUserUseCase(EmailPort emailPort, UserPort userPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        return new RegisterUserUseCase(userPort, emailPort, tokenGeneratorPort, refreshTokenPort);
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

    @Value("${MAIL_USERNAME}")
    String username;
    @Value("${MAIL_PASSWORD}")
    String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
