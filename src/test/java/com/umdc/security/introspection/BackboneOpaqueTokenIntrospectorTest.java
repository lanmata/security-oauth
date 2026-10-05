package com.umdc.security.introspection;

import com.umdc.security.client.BackbonePublicClient;
import com.umdc.security.client.to.ManagedClientTokenIntrospectRequest;
import com.umdc.security.client.to.ManagedClientTokenIntrospectResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.BadOpaqueTokenException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BackboneOpaqueTokenIntrospectorTest {

    private final BackbonePublicClient backbonePublicClient = mock(BackbonePublicClient.class);
    private final BackboneOpaqueTokenIntrospector introspector =
            new BackboneOpaqueTokenIntrospector(backbonePublicClient);

    @Test
    @DisplayName("introspect returns a principal with SCOPE_ authorities for an active token")
    void introspectReturnsPrincipalForActiveToken() {
        var response = new ManagedClientTokenIntrospectResponse(
                true, "client-123", "mercury-integration",
                List.of("mercury:read", "mercury:write"),
                "backbone-rest", 9999999999L, 1000000000L, "jti-abc");
        when(backbonePublicClient.introspect(any(ManagedClientTokenIntrospectRequest.class))).thenReturn(response);

        OAuth2AuthenticatedPrincipal principal = introspector.introspect("valid-token");

        assertEquals("client-123", principal.getName());
        assertTrue(principal.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("SCOPE_mercury:read")));
        assertTrue(principal.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("SCOPE_mercury:write")));
    }

    @Test
    @DisplayName("introspect tolerates an active response with optional fields left null")
    void introspectHandlesActiveResponseWithNullOptionalFields() {
        var response = new ManagedClientTokenIntrospectResponse(
                true, "client-456", "another-client", null, null, null, null, null);
        when(backbonePublicClient.introspect(any(ManagedClientTokenIntrospectRequest.class))).thenReturn(response);

        OAuth2AuthenticatedPrincipal principal = introspector.introspect("valid-token-no-scopes");

        assertEquals("client-456", principal.getName());
        assertTrue(principal.getAuthorities().isEmpty());
    }

    @Test
    @DisplayName("introspect rejects a token backbone reports as inactive")
    void introspectRejectsInactiveToken() {
        var response = new ManagedClientTokenIntrospectResponse(
                false, null, null, null, null, null, null, null);
        when(backbonePublicClient.introspect(any(ManagedClientTokenIntrospectRequest.class))).thenReturn(response);

        assertThrows(BadOpaqueTokenException.class, () -> introspector.introspect("revoked-token"));
    }

    @Test
    @DisplayName("introspect fails closed when backbone is unreachable")
    void introspectFailsClosedOnBackboneFailure() {
        when(backbonePublicClient.introspect(any(ManagedClientTokenIntrospectRequest.class)))
                .thenThrow(new RuntimeException("connection refused"));

        assertThrows(BadOpaqueTokenException.class, () -> introspector.introspect("any-token"));
    }
}
