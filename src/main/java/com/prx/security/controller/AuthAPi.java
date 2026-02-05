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

/**
 * REST API contract for authentication operations.
 *
 * <p>This interface defines the endpoints used to generate session tokens and
 * authenticate clients. Implementations should provide the actual business
 * logic by implementing or wiring an {@link AuthService}.</p>
 *
 * <p>Important details:
 * <ul>
 *   <li>Clients MUST provide the header named by {@link #BACKBONE_SESSION_TOKEN} when required.</li>
 *   <li>Responses are JSON and conform to {@link AuthResponse} on success.</li>
 * </ul>
 * </p>
 */
@Tag(name = "auth", description = "The authenticate API")
public interface AuthAPi {

    /**
     * Name of the custom request header that carries a backbone session token.
     * Implementations should validate this header as needed.
     */
    String BACKBONE_SESSION_TOKEN = "session-token-bkd";

    /**
     * Return an {@link AuthService} instance used by the default endpoint
     * implementations. Concrete controllers may override this to provide a
     * real service (for example, by autowiring a Spring bean).
     */
    default AuthService getService() {
        return new AuthService() {
        };
    }

    /**
     * Generates an authentication session token based on the provided
     * {@link AuthRequest}.
     *
     * <p>Notes for implementers and callers:
     * <ul>
     *   <li>The caller must provide the backbone session header specified by
     *       {@link #BACKBONE_SESSION_TOKEN}. That header may be validated by the
     *       underlying {@link AuthService} implementation; this interface only
     *       declares the requirement.</li>
     *   <li>On success returns HTTP 200 with a JSON body containing an
     *       {@link AuthResponse} instance with the generated session token.
     *   </li>
     * </ul>
     * </p>
     *
     * @param sessionTokenBkd the backbone session token header value. May be
     *                        required for authorization/validation by the
     *                        service implementation
     * @param authRequest     the authentication request containing credentials
     *                        or alias information; must not be null
     * @return ResponseEntity containing the authentication response with the
     *         generated session token or an appropriate error status
     * @see AuthRequest
     * @see AuthResponse
     * @see AuthService
     */
    @Operation(summary = "Generate a session token", description = "Generates a session token based on the provided authentication request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated session token"),
            @ApiResponse(responseCode = "400", description = "Invalid session token or authentication request"),
            @ApiResponse(responseCode = "406", description = "Not acceptable response")
    })
    @PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unused")
    default ResponseEntity<AuthResponse> accessToken(@RequestHeader(BACKBONE_SESSION_TOKEN) String sessionTokenBkd, @RequestBody AuthRequest authRequest) {
        return this.getService().token(authRequest);
    }

    /**
     * Generates a session token using an explicit session-token header and the
     * provided {@link AuthRequest}.
     *
     * <p>This endpoint is provided for clients that supply the session token
     * as part of the request body/header and want the service to consider it
     * when creating the returned session token.</p>
     *
     * @param sessionTokenBkd the backbone session token header value. Service
     *                        implementations may use this to seed or validate
     *                        the generated token
     * @param authRequest     the authentication request containing credentials
     *                        or alias information; must not be null
     * @return ResponseEntity containing the authentication response with the
     *         generated session token or an appropriate error status
     * @see AuthRequest
     * @see AuthResponse
     * @see AuthService
     */
    @Operation(summary = "Generate a session token", description = "Generates a session token based on the provided authentication request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated session token"),
            @ApiResponse(responseCode = "400", description = "Invalid session token or authentication request"),
            @ApiResponse(responseCode = "406", description = "Not acceptable response")
    })
    @PostMapping(path = "/session-token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unused")
    default ResponseEntity<AuthResponse> generateTokenSession(@RequestHeader(BACKBONE_SESSION_TOKEN) String sessionTokenBkd, @RequestBody AuthRequest authRequest) {
        return this.getService().token(authRequest, sessionTokenBkd);
    }

}
