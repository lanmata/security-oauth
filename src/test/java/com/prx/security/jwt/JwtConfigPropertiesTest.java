package com.prx.security.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtConfigPropertiesTest {

    @Test
    @DisplayName("Should create JwtConfigProperties with default constructor")
    void shouldCreateJwtConfigPropertiesWithDefaultConstructor() {
        JwtConfigProperties jwtConfigProperties = new JwtConfigProperties();
        assertNull(jwtConfigProperties.getSecret());
        assertEquals(0, jwtConfigProperties.getExpirationMs());
    }

    @Test
    @DisplayName("Should set and get secret")
    void shouldSetAndGetSecret() {
        JwtConfigProperties jwtConfigProperties = new JwtConfigProperties();
        jwtConfigProperties.setSecret("mySecret");
        assertEquals("mySecret", jwtConfigProperties.getSecret());
    }

    @Test
    @DisplayName("Should set and get expirationMs")
    void shouldSetAndGetExpirationMs() {
        JwtConfigProperties jwtConfigProperties = new JwtConfigProperties();
        jwtConfigProperties.setExpirationMs(3600000L);
        assertEquals(3600000L, jwtConfigProperties.getExpirationMs());
    }

    @Test
    @DisplayName("Should handle null secret")
    void shouldHandleNullSecret() {
        JwtConfigProperties jwtConfigProperties = new JwtConfigProperties();
        jwtConfigProperties.setSecret(null);
        assertNull(jwtConfigProperties.getSecret());
    }

    @Test
    @DisplayName("Should handle zero expirationMs")
    void shouldHandleZeroExpirationMs() {
        JwtConfigProperties jwtConfigProperties = new JwtConfigProperties();
        jwtConfigProperties.setExpirationMs(0L);
        assertEquals(0L, jwtConfigProperties.getExpirationMs());
    }
}
