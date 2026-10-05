package com.umdc.security;

import com.umdc.security.exception.CertificateSecurityException;
import com.umdc.security.service.SessionJwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SessionJwtServiceTest {

    @Mock
    private SessionJwtService sessionJwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsTokenExpired() throws CertificateSecurityException {
        String token = "token";
        when(sessionJwtService.isTokenExpired(anyString())).thenReturn(true);
        assertTrue(sessionJwtService.isTokenExpired(token));
    }

    @Test
    void testIsValid() {
        String token = "token";
        assertFalse(sessionJwtService.isValid(token));
    }

    @Test
    void testGetKeycloakUserIdFromToken() {
        String token = "token";
        assertTrue(sessionJwtService.getKeycloakUserIdFromToken(token).isEmpty());
    }
}
