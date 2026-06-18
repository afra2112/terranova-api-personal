package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.GoogleUserInfo;
import com.terranova.api.v1.auth.domain.ports.out.GoogleAuthPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import com.terranova.api.v1.user.domain.AuthProviderEnum;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

public class LinkGoogleAccountUseCase {

    private final GoogleAuthPort googleAuthPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AuthFacade authFacade;

    public LinkGoogleAccountUseCase(
            GoogleAuthPort googleAuthPort,
            UserRepositoryPort userRepositoryPort,
            AuthFacade authFacade
    ) {
        this.googleAuthPort = googleAuthPort;
        this.userRepositoryPort = userRepositoryPort;
        this.authFacade = authFacade;
    }

    public void link(String idToken){

        GoogleUserInfo googleUser = googleAuthPort.verifyToken(idToken);

        User authenticatedUser = userRepositoryPort.findById(authFacade.getAuthenticatedId());

        validateEmailMatch(
                authenticatedUser,
                googleUser
        );

        validateGoogleNotLinked(
                authenticatedUser
        );

        validateGoogleAccountNotUsed(
                authenticatedUser,
                googleUser
        );

        User updated = authenticatedUser.toBuilder()
                .googleId(googleUser.googleId())
                .authProvider(
                        authenticatedUser.authProvider() == null
                                ? AuthProviderEnum.GOOGLE
                                : authenticatedUser.authProvider()
                )

                .profilePicture(
                        authenticatedUser.profilePicture() == null
                                ? googleUser.picture()
                                : authenticatedUser.profilePicture()
                )
                .build();

        userRepositoryPort.save(updated);
    }

    private void validateEmailMatch(
            User user,
            GoogleUserInfo googleUser
    ){
        if(
                !user.email()
                        .equalsIgnoreCase(
                                googleUser.email()
                        )
        ){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_GOOGLE_ACCOUNT,
                    "Google account email does not match authenticated user. Current user email: " + user.email() + ". Google email provided: " + googleUser.email()
            );
        }
    }

    private void validateGoogleNotLinked(
            User user
    ){
        if(user.googleId() != null){
            throw new BusinessException(
                    ErrorCodeEnum.GOOGLE_ALREADY_LINKED,
                    "Google account already linked. Google email: " + user.email()
            );
        }
    }

    private void validateGoogleAccountNotUsed(
            User currentUser,
            GoogleUserInfo googleUser
    ){

        userRepositoryPort.findByGoogleId(googleUser.googleId())
                .ifPresent(existing -> {
                    if(!existing.userId().equals(currentUser.userId())){
                        throw new BusinessException(
                                ErrorCodeEnum.GOOGLE_ACCOUNT_ALREADY_USED,
                                "Google account already linked to another user"
                        );
                    }
                });
    }
}