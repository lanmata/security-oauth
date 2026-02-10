package com.prx.security.service;

import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthServiceTest {

    private final AuthService authService = new AuthService() {};

    @Test
    @DisplayName("token should return NOT_IMPLEMENTED status")
    void tokenShouldReturnNotImplementedStatus() {
        AuthRequest authRequest = new AuthRequest("", "");
        ResponseEntity<AuthResponse> response = authService.token(authRequest);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
    }

    @Test
    @DisplayName("validate should throw NotImplementedException")
    void validateShouldThrowNotImplementedException() {
        String sessionTokenBkd = "dummyToken";
        assertThrows(NotImplementedException.class, () -> authService.validate(sessionTokenBkd));
    }
}
