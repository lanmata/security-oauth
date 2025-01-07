package com.prx.security.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthPropertiesTest {

    @Test
    @DisplayName("Should create AuthProperties with default constructor")
    void shouldCreateAuthPropertiesWithDefaultConstructor() {
        AuthProperties properties = new AuthProperties();
        assertNull(properties.getAuthorizationGrantType());
        assertNull(properties.getScope());
        assertNull(properties.getClientSecret());
        assertNull(properties.getClientId());
        assertNull(properties.getRedirectUri());
        assertNull(properties.getUsername());
        assertNull(properties.getPassword());
    }

    @Test
    @DisplayName("Should set and get authorization grant type")
    void shouldSetAndGetAuthorizationGrantType() {
        AuthProperties properties = new AuthProperties();
        properties.setAuthorizationGrantType("authorizationGrantType");
        assertEquals("authorizationGrantType", properties.getAuthorizationGrantType());
    }

    @Test
    @DisplayName("Should set and get scope")
    void shouldSetAndGetScope() {
        AuthProperties properties = new AuthProperties();
        properties.setScope("scope");
        assertEquals("scope", properties.getScope());
    }

    @Test
    @DisplayName("Should set and get client secret")
    void shouldSetAndGetClientSecret() {
        AuthProperties properties = new AuthProperties();
        properties.setClientSecret("clientSecret");
        assertEquals("clientSecret", properties.getClientSecret());
    }

    @Test
    @DisplayName("Should set and get client ID")
    void shouldSetAndGetClientId() {
        AuthProperties properties = new AuthProperties();
        properties.setClientId("clientId");
        assertEquals("clientId", properties.getClientId());
    }

    @Test
    @DisplayName("Should set and get redirect URI")
    void shouldSetAndGetRedirectUri() {
        AuthProperties properties = new AuthProperties();
        properties.setRedirectUri("redirectUri");
        assertEquals("redirectUri", properties.getRedirectUri());
    }

    @Test
    @DisplayName("Should set and get username")
    void shouldSetAndGetUsername() {
        AuthProperties properties = new AuthProperties();
        properties.setUsername("username");
        assertEquals("username", properties.getUsername());
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        AuthProperties properties = new AuthProperties();
        properties.setPassword("password");
        assertEquals("password", properties.getPassword());
    }

    @Test
    @DisplayName("Should handle null authorization grant type")
    void shouldHandleNullAuthorizationGrantType() {
        AuthProperties properties = new AuthProperties();
        properties.setAuthorizationGrantType(null);
        assertNull(properties.getAuthorizationGrantType());
    }

    @Test
    @DisplayName("Should handle null scope")
    void shouldHandleNullScope() {
        AuthProperties properties = new AuthProperties();
        properties.setScope(null);
        assertNull(properties.getScope());
    }

    @Test
    @DisplayName("Should handle null client secret")
    void shouldHandleNullClientSecret() {
        AuthProperties properties = new AuthProperties();
        properties.setClientSecret(null);
        assertNull(properties.getClientSecret());
    }

    @Test
    @DisplayName("Should handle null client ID")
    void shouldHandleNullClientId() {
        AuthProperties properties = new AuthProperties();
        properties.setClientId(null);
        assertNull(properties.getClientId());
    }

    @Test
    @DisplayName("Should handle null redirect URI")
    void shouldHandleNullRedirectUri() {
        AuthProperties properties = new AuthProperties();
        properties.setRedirectUri(null);
        assertNull(properties.getRedirectUri());
    }

    @Test
    @DisplayName("Should handle null username")
    void shouldHandleNullUsername() {
        AuthProperties properties = new AuthProperties();
        properties.setUsername(null);
        assertNull(properties.getUsername());
    }

    @Test
    @DisplayName("Should handle null password")
    void shouldHandleNullPassword() {
        AuthProperties properties = new AuthProperties();
        properties.setPassword(null);
        assertNull(properties.getPassword());
    }
}
