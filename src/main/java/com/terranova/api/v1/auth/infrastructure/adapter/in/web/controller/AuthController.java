package com.terranova.api.v1.auth.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.auth.application.usecase.LoginUseCase;
import com.terranova.api.v1.auth.application.usecase.LogoutUseCase;
import com.terranova.api.v1.auth.application.usecase.RegisterUserUseCase;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final RegisterUserUseCase registerUserUseCase;
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
//
//    @PostMapping("/refresh")
//    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
//        return ResponseEntity.ok(authService.refreshToken(request));
//    }
}
