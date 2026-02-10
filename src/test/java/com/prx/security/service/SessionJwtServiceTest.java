package com.prx.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static com.prx.security.constant.ConstantApp.SESSION_TOKEN_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionJwtServiceTest {

    private final String token = "dummy-token";

    @Test
    @DisplayName("isTokenExpired should return false when token expiration is in the future")
    void isTokenExpired_futureExpiration() throws Exception {
        Claims claims = mock(Claims.class);
        Date future = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        when(claims.getExpiration()).thenReturn(future);

        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return claims;
            }
        };

        assertFalse(svc.isTokenExpired(token));
    }

    @Test
    @DisplayName("isTokenExpired should return true when token expiration is in the past")
    void isTokenExpired_pastExpiration() throws Exception {
        Claims claims = mock(Claims.class);
        Date past = Date.from(LocalDateTime.now().minusHours(2).atZone(ZoneId.systemDefault()).toInstant());
        when(claims.getExpiration()).thenReturn(past);

        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return claims;
            }
        };

        assertTrue(svc.isTokenExpired(token));
    }

    @Test
    @DisplayName("isTokenExpired should return true when getTokenClaims throws ExpiredJwtException")
    void isTokenExpired_expiredJwtException() throws Exception {
        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                throw new ExpiredJwtException(null, null, "expired");
            }
        };

        assertTrue(svc.isTokenExpired(token));
    }

    @Test
    @DisplayName("isTokenExpired should throw checked Exception if getTokenClaims throws a checked Exception")
    void isTokenExpired_checkedException() {
        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return null;
            }
        };

        assertThrows(Exception.class, () -> svc.isTokenExpired(token));
    }

    @Test
    @DisplayName("isValid should return true for valid token with correct type and not expired")
    void isValid_validToken() {
        Claims claims = mock(Claims.class);
        Date future = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        when(claims.getExpiration()).thenReturn(future);
        when(claims.get("type")).thenReturn(SESSION_TOKEN_KEY);

        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return claims;
            }
        };

        assertTrue(svc.isValid(token));
    }

    @Test
    @DisplayName("isValid should return false when type does not match")
    void isValid_wrongType() {
        Claims claims = mock(Claims.class);
        Date future = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        when(claims.getExpiration()).thenReturn(future);
        when(claims.get("type")).thenReturn("other");

        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return claims;
            }
        };

        assertFalse(svc.isValid(token));
    }

    @Test
    @DisplayName("isValid should return false when getTokenClaims throws an exception")
    void isValid_exception() {
        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                throw new RuntimeException("boom");
            }
        };

        assertFalse(svc.isValid(token));
    }

    @Test
    @DisplayName("getKeycloakUserIdFromToken should return subject when present")
    void getKeycloakUserIdFromToken_present() {
        Claims claims = mock(Claims.class);
        when(claims.getSubject()).thenReturn("user-123");

        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                return claims;
            }
        };

        Optional<String> opt = svc.getKeycloakUserIdFromToken(token);
        assertTrue(opt.isPresent());
        assertEquals("user-123", opt.get());
    }

    @Test
    @DisplayName("getKeycloakUserIdFromToken should return empty when getTokenClaims throws")
    void getKeycloakUserIdFromToken_exception() {
        SessionJwtService svc = new SessionJwtService() {
            @Override
            public String generateSessionToken(String username, Map<String, String> parameters) {
                return null;
            }

            @Override
            public String getUsernameFromToken(String token) {
                return null;
            }

            @Override
            public Claims getTokenClaims(String token) {
                throw new RuntimeException("boom");
            }
        };

        Optional<String> opt = svc.getKeycloakUserIdFromToken(token);
        assertTrue(opt.isEmpty());
    }
}

