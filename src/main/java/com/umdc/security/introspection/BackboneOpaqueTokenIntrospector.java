package com.umdc.security.introspection;

import com.umdc.security.client.BackbonePublicClient;
import com.umdc.security.client.to.ManagedClientTokenIntrospectRequest;
import com.umdc.security.client.to.ManagedClientTokenIntrospectResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.BadOpaqueTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Shared {@link OpaqueTokenIntrospector} that validates a backbone-issued M2M
 * token by calling backbone-rest's own introspection endpoint, rather than
 * verifying the JWT signature locally.
 * <p>
 * Any UMDC service that needs to accept an M2M token minted by backbone-rest's
 * Managed Client Authentication Manager (MCAM) should wire this bean into its
 * own {@code oauth2ResourceServer().opaqueToken(...)} configuration instead of
 * writing another introspector — the token's validity (including immediate
 * revocation) is authoritative only at backbone, never derivable from the
 * signature alone. See backbone-rest's {@code ManagedClientTokenServiceImpl}
 * for why: active state also depends on Redis-side revocation, not just the
 * JWT's signature/expiry.
 * </p>
 */
@Component
@ConditionalOnClass(name = "org.springframework.cloud.openfeign.FeignClient")
public class BackboneOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final BackbonePublicClient backbonePublicClient;

    public BackboneOpaqueTokenIntrospector(BackbonePublicClient backbonePublicClient) {
        this.backbonePublicClient = backbonePublicClient;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        ManagedClientTokenIntrospectResponse response;
        try {
            response = backbonePublicClient.introspect(new ManagedClientTokenIntrospectRequest(token));
        } catch (Exception e) {
            throw new OAuth2IntrospectionException("Unable to reach backbone introspection endpoint", e);
        }
        if (Objects.isNull(response) || !response.active()) {
            throw new BadOpaqueTokenException("Token is not active");
        }

        List<String> scopes = Objects.isNull(response.scopes()) ? List.of() : response.scopes();

        Map<String, Object> claims = new HashMap<>();
        claims.put(OAuth2TokenIntrospectionClaimNames.ACTIVE, response.active());
        if (Objects.nonNull(response.clientId())) {
            claims.put(OAuth2TokenIntrospectionClaimNames.CLIENT_ID, response.clientId());
        }
        claims.put(OAuth2TokenIntrospectionClaimNames.SCOPE, scopes);
        if (Objects.nonNull(response.jti())) {
            claims.put(OAuth2TokenIntrospectionClaimNames.JTI, response.jti());
        }
        if (Objects.nonNull(response.exp())) {
            claims.put(OAuth2TokenIntrospectionClaimNames.EXP, Instant.ofEpochSecond(response.exp()));
        }
        if (Objects.nonNull(response.iat())) {
            claims.put(OAuth2TokenIntrospectionClaimNames.IAT, Instant.ofEpochSecond(response.iat()));
        }
        if (Objects.nonNull(response.clientName())) {
            claims.put("client_name", response.clientName());
        }

        List<GrantedAuthority> authorities = scopes.stream()
                .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                .collect(Collectors.toList());

        return new OAuth2IntrospectionAuthenticatedPrincipal(response.clientId(), claims, authorities);
    }
}
