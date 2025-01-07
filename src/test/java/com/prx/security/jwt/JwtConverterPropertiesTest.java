package com.prx.security.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtConverterPropertiesTest {

    @Test
    @DisplayName("Should create JwtConverterProperties with default constructor")
    void shouldCreateJwtConverterPropertiesWithDefaultConstructor() {
        JwtConverterProperties jwtConverterProperties = new JwtConverterProperties();
        assertNull(jwtConverterProperties.getResourceId());
        assertNull(jwtConverterProperties.getPrincipalClaimName());
    }

    @Test
    @DisplayName("Should set and get resourceId")
    void shouldSetAndGetResourceId() {
        JwtConverterProperties jwtConverterProperties = new JwtConverterProperties();
        jwtConverterProperties.setResourceId("resourceId");
        assertEquals("resourceId", jwtConverterProperties.getResourceId());
    }

    @Test
    @DisplayName("Should set and get principalClaimName")
    void shouldSetAndGetPrincipalClaimName() {
        JwtConverterProperties jwtConverterProperties = new JwtConverterProperties();
        jwtConverterProperties.setPrincipalClaimName("principalClaimName");
        assertEquals("principalClaimName", jwtConverterProperties.getPrincipalClaimName());
    }

    @Test
    @DisplayName("Should handle null resourceId")
    void shouldHandleNullResourceId() {
        JwtConverterProperties jwtConverterProperties = new JwtConverterProperties();
        jwtConverterProperties.setResourceId(null);
        assertNull(jwtConverterProperties.getResourceId());
    }

    @Test
    @DisplayName("Should handle null principalClaimName")
    void shouldHandleNullPrincipalClaimName() {
        JwtConverterProperties jwtConverterProperties = new JwtConverterProperties();
        jwtConverterProperties.setPrincipalClaimName(null);
        assertNull(jwtConverterProperties.getPrincipalClaimName());
    }
}
