package com.prx.security.controller;

import com.prx.security.service.AuthService;
import com.prx.security.to.AuthRequest;
import com.prx.security.to.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@Tag(name = "auth", description = "The authenticate API")
public interface AuthAPi {
     String BACKBONE_SESSION_TOKEN = "session-token-bkd";
    default AuthService getService() {
        return new AuthService() {
        };
    }

    /// Generates a session token based on the provided authentication request.
    ///
    /// @param sessionTokenBkd the session token to validate
    /// @param authRequest     the authentication request containing user alias
    /// @return ResponseEntity containing the authentication response with the session token
    /// @see AuthRequest
    /// @see AuthResponse
    /// @see AuthService
    /// @see ResponseEntity
    @Operation(summary = "Generate a session token", description = "Generates a session token based on the provided authentication request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated session token"),
            @ApiResponse(responseCode = "400", description = "Invalid session token or authentication request"),
            @ApiResponse(responseCode = "406", description = "Not acceptable response")
    })
    @PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<AuthResponse> accessToken(@RequestHeader(BACKBONE_SESSION_TOKEN) String sessionTokenBkd, @RequestBody AuthRequest authRequest) {
        return this.getService().token(authRequest);
    }

}
