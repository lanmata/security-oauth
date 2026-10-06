package com.umdc.security.client.to;

import java.util.List;

/**
 * Response body from backbone-rest's M2M token introspection endpoint
 * ({@code POST /api/v1/managed-clients/introspect}).
 * <p>
 * Per RFC 7662, backbone-rest always returns HTTP 200 here. When the token is
 * invalid, expired, or revoked, {@code active} is {@code false} and every
 * other field is {@code null}.
 * </p>
 *
 * @param active     whether the token is currently valid, not expired, and not revoked
 * @param clientId   the managed client UUID (subject claim); {@code null} when inactive
 * @param clientName the human-readable client name; {@code null} when inactive
 * @param scopes     the granted scopes; {@code null} when inactive
 * @param issuer     the issuer claim; {@code null} when inactive
 * @param exp        expiry time as Unix epoch seconds; {@code null} when inactive
 * @param iat        issued-at time as Unix epoch seconds; {@code null} when inactive
 * @param jti        the unique token identifier; {@code null} when inactive
 */
public record ManagedClientTokenIntrospectResponse(
        boolean active,
        String clientId,
        String clientName,
        List<String> scopes,
        String issuer,
        Long exp,
        Long iat,
        String jti
) {
}
