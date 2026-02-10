package com.prx.security.controller;

import com.prx.security.service.AuthService;
import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes authentication endpoints.
 * <p>
 * Implements `AuthAPi` to provide token generation and validation endpoints.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthApiController implements AuthAPi {

    /**
     * The authentication service used to perform token operations.
     */
    private final AuthService authService;

    /**
     * Creates a new instance of {@link AuthApiController}.
     *
     * @param authService the authentication service
     */
    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Validates the provided session token before requesting a token from the service.
     * </p>
     */
    @Override
    public ResponseEntity<AuthResponse> accessToken(String sessionTokenBkd, AuthRequest authRequest) {
        boolean isValid = authService.validate(sessionTokenBkd);
        if (isValid) {
            return authService.token(authRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthResponse> generateTokenSession(@RequestHeader(BACKBONE_SESSION_TOKEN) String sessionTokenBkd, @RequestBody AuthRequest authRequest) {
        return authService.token(authRequest, sessionTokenBkd);
    }
}
