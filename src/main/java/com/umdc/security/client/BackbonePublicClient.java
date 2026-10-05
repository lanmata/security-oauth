package com.umdc.security.client;

import com.umdc.security.client.to.ManagedClientTokenIntrospectRequest;
import com.umdc.security.client.to.ManagedClientTokenIntrospectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.umdc.security.constant.ConstantApp.SESSION_TOKEN_KEY;

/**
 * Feign client for backbone-rest's public ({@code permitAll}) authentication
 * endpoints: session-token validation and M2M token introspection.
 * <p>
 * Every UMDC service that needs to accept a backbone-issued token — a user
 * session token or an M2M managed-client token — should depend on this shared
 * client instead of hand-rolling its own, so the contract lives in one place.
 * </p>
 * <p>
 * Deliberately carries no outbound-auth {@code configuration}: these backbone
 * endpoints are public, so attaching a service's own outbound Bearer token
 * here would only add an unnecessary round-trip (and a false dependency on
 * whatever issues that outbound token) to what should be a fast, self-contained
 * check. Registered automatically for any consumer whose
 * {@code @SpringBootApplication(scanBasePackages=...)} already includes
 * {@code com.umdc.security} — see {@link BackboneFeignClientConfig}.
 * </p>
 */
@FeignClient(name = "backbonePublicClient", url = "${umdc.backbone.base-url:https://api.umdc-qa.tst/backbone}")
public interface BackbonePublicClient {

    @GetMapping("/api/v1/session/validate")
    boolean validate(@RequestHeader(SESSION_TOKEN_KEY) String sessionToken);

    @PostMapping("/api/v1/managed-clients/introspect")
    ManagedClientTokenIntrospectResponse introspect(@RequestBody ManagedClientTokenIntrospectRequest request);

}
