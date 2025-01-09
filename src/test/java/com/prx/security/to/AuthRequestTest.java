package com.prx.security.to;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    @DisplayName("Should create AuthRequest with default constructor")
    void shouldCreateAuthRequestWithDefaultConstructor() {
        AuthRequest authRequest = new AuthRequest(null, null);
        assertNull(authRequest.alias());
        assertNull(authRequest.password());
    }

    @Test
    @DisplayName("Should create AuthRequest with parameterized constructor")
    void shouldCreateAuthRequestWithParameterizedConstructor() {
        AuthRequest authRequest = new AuthRequest("userAlias", "userPassword");
        assertEquals("userAlias", authRequest.alias());
        assertEquals("userPassword", authRequest.password());
    }

    @Test
    @DisplayName("Should set and get alias")
    void shouldSetAndGetAlias() {
        AuthRequest authRequest = new AuthRequest("newAlias", "newPassword");
        assertEquals("newAlias", authRequest.alias());
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        AuthRequest authRequest = new AuthRequest("", "newPassword");
        assertEquals("newPassword", authRequest.password());
    }

    @Test
    @DisplayName("Should handle null alias")
    void shouldHandleNullAlias() {
        AuthRequest authRequest = new AuthRequest(null, "password");
        assertNull(authRequest.alias());
    }

    @Test
    @DisplayName("Should handle null password")
    void shouldHandleNullPassword() {
        AuthRequest authRequest = new AuthRequest(null,null);
        assertNull(authRequest.password());
    }
}
