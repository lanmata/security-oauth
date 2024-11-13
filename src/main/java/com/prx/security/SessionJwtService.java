package com.prx.security;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang.NotImplementedException;

import java.util.Date;
import java.util.Optional;

import static com.prx.security.constant.ConstantApp.SESSION_TOKEN_KEY;

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
    default boolean isTokenExpired(String token) {
        try {
            Claims claims = getTokenClaims(token);
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
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
            return SESSION_TOKEN_KEY.equals(getTokenClaims(token).get("type")) && new Date().before(claims.getExpiration());
        } catch (Exception e) {
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
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Generates a session token for the given username.
     *
     * @param username the username for which to generate the session token
     * @return the generated session token
     */
    String generateSessionToken(String username);

    /**
     * Retrieves the claims from the given token.
     *
     * @param token the JWT token
     * @return the claims contained in the token
     */
    default Claims getTokenClaims(String token) {
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
