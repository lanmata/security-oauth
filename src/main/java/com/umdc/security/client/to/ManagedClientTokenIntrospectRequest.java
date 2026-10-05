package com.umdc.security.client.to;

/**
 * Request body for backbone-rest's M2M token introspection endpoint
 * ({@code POST /api/v1/managed-clients/introspect}).
 *
 * @param token the opaque M2M access token to introspect
 */
public record ManagedClientTokenIntrospectRequest(String token) {
}
