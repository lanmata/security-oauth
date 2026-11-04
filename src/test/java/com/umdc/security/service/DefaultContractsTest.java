package com.umdc.security.service;

import com.umdc.security.to.AuthRequest;
import com.umdc.security.to.AuthResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/** Verifies the default behaviour of the service contracts. */
class DefaultContractsTest {

    private final SessionJwtService sessionJwtService = new SessionJwtService() {
        @Override
        public String generateSessionToken(String username, Map<String, String> parameters) {
            return "token";
        }

        @Override
        public String getUsernameFromToken(String token) {
            return "user";
        }
    };

    private final AuthService authService = new AuthService() {
    };

    @Test
    void getTokenClaimsIsNotImplementedByDefault() {
        assertThrows(NotImplementedException.class, () -> sessionJwtService.getTokenClaims("t"));
    }

    @Test
    void isValidReturnsFalseWhenClaimsCannotBeRead() {
        assertFalse(sessionJwtService.isValid("t"));
    }

    @Test
    void keycloakUserIdIsEmptyWhenClaimsCannotBeRead() {
        assertTrue(sessionJwtService.getKeycloakUserIdFromToken("t").isEmpty());
    }

    @Test
    void tokenWithSessionTokenIsNotImplementedByDefault() {
        ResponseEntity<AuthResponse> response = authService.token(new AuthRequest("alias", "pwd"), "session");
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
    }

    @Test
    void tokenIsNotImplementedByDefault() {
        assertEquals(HttpStatus.NOT_IMPLEMENTED, authService.token(new AuthRequest("alias", "pwd")).getStatusCode());
    }

    @Test
    void validateIsNotImplementedByDefault() {
        assertThrows(NotImplementedException.class, () -> authService.validate("s"));
    }
}
