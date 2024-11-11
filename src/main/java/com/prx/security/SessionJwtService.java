package com.prx.security;

import io.jsonwebtoken.Claims;

import java.util.Optional;

/**
 * Create 2024-11-11
 * @author Luis Mata
 *
 * @since 17
 */
public interface SessionJwtService {

    /**
     * Checks if the given token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    boolean isTokenExpired(String token);

    /**
     * Retrieves the Keycloak user ID from the given token.
     *
     * @param token the JWT token
     * @return an Optional containing the user ID if present, otherwise an empty Optional
     */
    Optional<String> getKeycloakUserIdFromToken(String token);

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
    Claims getTokenClaims(String token);

    /**
     * Retrieves the username from the given token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    String getUsernameFromToken(String token);
}
