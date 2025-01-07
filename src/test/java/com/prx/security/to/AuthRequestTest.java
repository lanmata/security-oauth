package com.prx.security.to;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    @DisplayName("Should create AuthRequest with default constructor")
    void shouldCreateAuthRequestWithDefaultConstructor() {
        AuthRequest authRequest = new AuthRequest();
        assertNull(authRequest.getAlias());
        assertNull(authRequest.getPassword());
    }

    @Test
    @DisplayName("Should create AuthRequest with parameterized constructor")
    void shouldCreateAuthRequestWithParameterizedConstructor() {
        AuthRequest authRequest = new AuthRequest("userAlias", "userPassword");
        assertEquals("userAlias", authRequest.getAlias());
        assertEquals("userPassword", authRequest.getPassword());
    }

    @Test
    @DisplayName("Should set and get alias")
    void shouldSetAndGetAlias() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setAlias("newAlias");
        assertEquals("newAlias", authRequest.getAlias());
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("newPassword");
        assertEquals("newPassword", authRequest.getPassword());
    }

    @Test
    @DisplayName("Should handle null alias")
    void shouldHandleNullAlias() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setAlias(null);
        assertNull(authRequest.getAlias());
    }

    @Test
    @DisplayName("Should handle null password")
    void shouldHandleNullPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword(null);
        assertNull(authRequest.getPassword());
    }
}
