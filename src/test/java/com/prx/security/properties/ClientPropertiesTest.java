package com.prx.security.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientPropertiesTest {

    @Test
    @DisplayName("Should create AuthProperties with default constructor")
    void shouldCreateClientPropertiesWithDefaultConstructor() {
        ClientProperties properties = new ClientProperties();
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
        ClientProperties properties = new ClientProperties();
        properties.setAuthorizationGrantType("authorizationGrantType");
        assertEquals("authorizationGrantType", properties.getAuthorizationGrantType());
    }

    @Test
    @DisplayName("Should set and get scope")
    void shouldSetAndGetScope() {
        ClientProperties properties = new ClientProperties();
        properties.setScope("scope");
        assertEquals("scope", properties.getScope());
    }

    @Test
    @DisplayName("Should set and get client secret")
    void shouldSetAndGetClientSecret() {
        ClientProperties properties = new ClientProperties();
        properties.setClientSecret("clientSecret");
        assertEquals("clientSecret", properties.getClientSecret());
    }

    @Test
    @DisplayName("Should set and get client ID")
    void shouldSetAndGetClientId() {
        ClientProperties properties = new ClientProperties();
        properties.setClientId("clientId");
        assertEquals("clientId", properties.getClientId());
    }

    @Test
    @DisplayName("Should set and get redirect URI")
    void shouldSetAndGetRedirectUri() {
        ClientProperties properties = new ClientProperties();
        properties.setRedirectUri("redirectUri");
        assertEquals("redirectUri", properties.getRedirectUri());
    }

    @Test
    @DisplayName("Should set and get username")
    void shouldSetAndGetUsername() {
        ClientProperties properties = new ClientProperties();
        properties.setUsername("username");
        assertEquals("username", properties.getUsername());
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        ClientProperties properties = new ClientProperties();
        properties.setPassword("password");
        assertEquals("password", properties.getPassword());
    }

    @Test
    @DisplayName("Should handle null authorization grant type")
    void shouldHandleNullAuthorizationGrantType() {
        ClientProperties properties = new ClientProperties();
        properties.setAuthorizationGrantType(null);
        assertNull(properties.getAuthorizationGrantType());
    }

    @Test
    @DisplayName("Should handle null scope")
    void shouldHandleNullScope() {
        ClientProperties properties = new ClientProperties();
        properties.setScope(null);
        assertNull(properties.getScope());
    }

    @Test
    @DisplayName("Should handle null client secret")
    void shouldHandleNullClientSecret() {
        ClientProperties properties = new ClientProperties();
        properties.setClientSecret(null);
        assertNull(properties.getClientSecret());
    }

    @Test
    @DisplayName("Should handle null client ID")
    void shouldHandleNullClientId() {
        ClientProperties properties = new ClientProperties();
        properties.setClientId(null);
        assertNull(properties.getClientId());
    }

    @Test
    @DisplayName("Should handle null redirect URI")
    void shouldHandleNullRedirectUri() {
        ClientProperties properties = new ClientProperties();
        properties.setRedirectUri(null);
        assertNull(properties.getRedirectUri());
    }

    @Test
    @DisplayName("Should handle null username")
    void shouldHandleNullUsername() {
        ClientProperties properties = new ClientProperties();
        properties.setUsername(null);
        assertNull(properties.getUsername());
    }

    @Test
    @DisplayName("Should handle null password")
    void shouldHandleNullPassword() {
        ClientProperties properties = new ClientProperties();
        properties.setPassword(null);
        assertNull(properties.getPassword());
    }
}
