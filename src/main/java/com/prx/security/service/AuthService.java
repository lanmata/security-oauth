package com.prx.security.service;

import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for handling authentication-related operations.
 */
public interface AuthService {

    /**
     * Generates a token based on the provided authentication request.
     *
     * @param authRequest the authentication request containing the necessary credentials
     * @return a ResponseEntity containing the authentication response and HTTP status
     */
    default ResponseEntity<AuthResponse> token(AuthRequest authRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Generates a token based on the provided authentication request.
     * @param authRequest the authentication request containing the necessary credentials
     * @param sessionTokenBkd the session token used for backend session validation
     * @return a ResponseEntity containing the authentication response and HTTP status
     */
    default ResponseEntity<AuthResponse> token(AuthRequest authRequest, String sessionTokenBkd) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Validates the provided session token.
     *
     * @param sessionTokenBkd the session token to be validated
     * @return true if the session token is valid, false otherwise
     * @throws NotImplementedException if the method is not implemented
     */
    default boolean validate(String sessionTokenBkd)  {
        throw new NotImplementedException("validate method NOT implemented!");
    }
}
