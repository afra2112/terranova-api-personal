package com.terranova.api.v1.auth.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.auth.application.usecase.*;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.FacebookLoginRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.GoogleLoginRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final GoogleLoginUseCase googleLoginUseCase;
    private final FacebookLoginUseCase facebookLoginUseCase;
    private final LinkGoogleAccountUseCase linkGoogleAccountUseCase;
    private final LinkFacebookAccountUseCase linkFacebookAccountUseCase;
    private final AuthMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) throws Exception {
        return ResponseEntity.ok(
                authMapper.toAuthResponse(
                        loginUseCase.login(
                                authMapper.toUserCredential(request)
                        )
                )
        );
    }

    @PostMapping("/facebook")
    public ResponseEntity<AuthResponse> loginWithFacebook(@RequestBody @Valid FacebookLoginRequest facebookLoginRequest){
        return ResponseEntity.ok(facebookLoginUseCase.login(facebookLoginRequest.accessToken()));
    }

    @PostMapping("/facebook/link")
    public ResponseEntity<Void> linkFacebook(@RequestBody @Valid FacebookLoginRequest request){
        linkFacebookAccountUseCase.link(request.accessToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> loginWithGoogle(@RequestBody @Valid GoogleLoginRequest request){
        return ResponseEntity.ok(googleLoginUseCase.login(request.idToken()));
    }

    @PostMapping("/google/link")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> linkGoogle(@RequestBody @Valid GoogleLoginRequest request){
        linkGoogleAccountUseCase.link(request.idToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(
                authMapper.toAuthResponse(registerUserUseCase.createUser(authMapper.fromRequestToNewUserDomain(request)))
        );
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@Valid @NotBlank @PathVariable String token){
        logoutUseCase.logout(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh/{token}")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @NotBlank @PathVariable String token){
        return ResponseEntity.ok(authMapper.toAuthResponse(refreshTokenUseCase.refreshToken(token)));
    }
}
