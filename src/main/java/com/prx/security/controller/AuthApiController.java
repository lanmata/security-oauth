package com.prx.security.controller;

import com.prx.security.service.AuthService;
import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// AuthApiController is a REST controller that handles authentication requests.
@RestController
@RequestMapping("/api/v1/auth")
public class AuthApiController implements AuthAPi {

    ///  The AuthService instance.
    private final AuthService authService;

    /// Constructor for AuthApiController.
    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    /// {@inheritDoc}
    @Override
    public ResponseEntity<AuthResponse> accessToken(String sessionTokenBkd, AuthRequest authRequest) {
        boolean isValid = authService.validate(sessionTokenBkd);
        if (isValid) {
            return authService.token(authRequest);
        }
        return ResponseEntity.badRequest().build();
    }
}
