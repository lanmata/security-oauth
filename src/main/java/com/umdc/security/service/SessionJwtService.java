package com.umdc.security.service;

import com.umdc.security.exception.CertificateSecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.NotImplementedException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

import static com.umdc.security.constant.ConstantApp.SESSION_TOKEN_KEY;

/**
 * Create 2024-11-11
 *
 * @author Luis Mata
 * @since 17
 */
public interface SessionJwtService {

    /**
     * Checks if the given token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    default boolean isTokenExpired(String token) throws CertificateSecurityException {
        try {
            Claims claims = getTokenClaims(token);
            return LocalDateTime.now(ZoneId.systemDefault()).isAfter(claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } catch (ExpiredJwtException _) {
            return true;
        }
    }

    /**
     * Validates the given token.
     *
     * @param token the JWT token
     * @return true if the token is valid, false otherwise
     */
    default boolean isValid(String token) {
        try {
            Claims claims = getTokenClaims(token);
            return SESSION_TOKEN_KEY.equals(claims.get("type")) && !isTokenExpired(token);
        } catch (Exception _) {
            return false;
        }
    }

    /**
     * Retrieves the Keycloak user ID from the given token.
     *
     * @param token the JWT token
     * @return an Optional containing the user ID if present, otherwise an empty Optional
     */
    default Optional<String> getKeycloakUserIdFromToken(String token) {
        try {
            Claims claims = getTokenClaims(token);
            return Optional.ofNullable(claims.getSubject());
        } catch (Exception _) {
            return Optional.empty();
        }
    }

    /**
     * Generates a session token for the given username and parameters.
     *
     * @param username   the username for which the session token is generated
     * @param parameters a map of additional parameters to include in the token
     * @return the generated session token
     */
    String generateSessionToken(String username, Map<String, String> parameters);

    /**
     * Retrieves the claims from the given token.
     *
     * @param token the JWT token
     * @return the claims contained in the token
     */
    default Claims getTokenClaims(String token) throws CertificateSecurityException {
        throw new NotImplementedException();
    }

    /**
     * Retrieves the username from the given token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    String getUsernameFromToken(String token);
}
