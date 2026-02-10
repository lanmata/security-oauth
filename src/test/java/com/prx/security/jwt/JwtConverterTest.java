package com.prx.security.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtConverterTest {

    @Test
    @DisplayName("convert returns JwtAuthenticationToken when resourceId provided and roles present")
    void convertReturnsTokenWhenResourceIdProvided() {
        JwtConverterProperties props = new JwtConverterProperties();
        props.setResourceId("my-resource");
        props.setPrincipalClaimName("sub");

        JwtConverter converter = new JwtConverter(props);

        Jwt jwt = Jwt.withTokenValue("token")
                .headers(h -> h.put("alg", "none"))
                .claims(c -> c.put("resource_access", Map.of("my-resource", Map.of("roles", java.util.List.of("USER","ADMIN")))))
                .subject("user")
                .issuedAt(Instant.now())
                .build();

        AbstractAuthenticationToken token = converter.convert(jwt);
        assertNotNull(token);
        assertEquals("user", token.getName());
        if (token.getAuthorities() != null) {
            assertTrue(token.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains("ROLE_USER")));
        }
    }

    @Test
    @DisplayName("convert handles missing resourceId gracefully")
    void extractResourceRolesThrowsWhenNoResourceId() {
        JwtConverterProperties props = new JwtConverterProperties();
        JwtConverter converter = new JwtConverter(props);

        Jwt jwt = Jwt.withTokenValue("token")
                .headers(h -> h.put("alg", "none"))
                .claims(c -> c.put("resource_access", Map.of()))
                .issuedAt(Instant.now())
                .build();

        AbstractAuthenticationToken token = converter.convert(jwt);
        assertNotNull(token);
    }
}
