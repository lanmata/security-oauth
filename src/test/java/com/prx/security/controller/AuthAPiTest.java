package com.prx.security.controller;

import com.prx.security.service.AuthService;
import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthAPiTest {

    private final AuthService authService = mock(AuthService.class);
    private final AuthAPi authAPi = new AuthAPi() {
        @Override
        public AuthService getService() {
            return authService;
        }
    };

    @Test
    @DisplayName("AuthService should not be null")
    void authServiceShouldNotBeNull() {
        AuthAPi authAPi = new AuthAPi() {
        };
        assertNotNull(authAPi.getService());
    }

    @Test
    @DisplayName("accessToken should return OK status with valid request")
    void accessTokenShouldReturnOkStatusWithValidRequest() {
        AuthRequest authRequest = new AuthRequest("validAlias", null);
        AuthResponse authResponse = new AuthResponse("validToken");
        when(authService.token(authRequest)).thenReturn(ResponseEntity.ok(authResponse));

        ResponseEntity<AuthResponse> response = authAPi.accessToken("validSessionToken", authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authResponse, response.getBody());
    }

    @Test
    @DisplayName("accessToken should return BAD_REQUEST status with null alias")
    void accessTokenShouldReturnBadRequestStatusWithNullAlias() {
        AuthRequest authRequest = new AuthRequest(null, null);
        when(authService.token(authRequest)).thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<AuthResponse> response = authAPi.accessToken("validSessionToken", authRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("accessToken should return BAD_REQUEST status with blank alias")
    void accessTokenShouldReturnBadRequestStatusWithBlankAlias() {
        AuthRequest authRequest = new AuthRequest("", "");
        when(authService.token(authRequest)).thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<AuthResponse> response = authAPi.accessToken("validSessionToken", authRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("accessToken should return NOT_ACCEPTABLE status with blank token")
    void accessTokenShouldReturnNotAcceptableStatusWithBlankToken() {
        AuthRequest authRequest = new AuthRequest("validAlias", "");
        when(authService.token(authRequest)).thenReturn(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());

        ResponseEntity<AuthResponse> response = authAPi.accessToken("", authRequest);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    @DisplayName("accessToken should return UNAUTHORIZED status with invalid session token")
    void accessTokenShouldReturnUnauthorizedStatusWithInvalidSessionToken() {
        AuthRequest authRequest = new AuthRequest("validAlias", "validPassword");
        when(authService.token(authRequest)).thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

        ResponseEntity<AuthResponse> response = authAPi.accessToken("invalidSessionToken", authRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
