package com.prx.security.to;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    @DisplayName("Should create AuthResponse with valid token")
    void shouldCreateAuthResponseWithValidToken() {
        AuthResponse authResponse = new AuthResponse("validToken");
        assertEquals("validToken", authResponse.token());
    }

    @Test
    @DisplayName("Should handle token with spaces")
    void shouldHandleTokenWithSpaces() {
        AuthResponse authResponse = new AuthResponse("  tokenWithSpaces  ");
        assertEquals("  tokenWithSpaces  ", authResponse.token());
    }
}
