package com.umdc.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for JWT handling.
 * <p>
 * Holds the JWT secret and expiration configuration used by JWT utilities.
 * </p>
 */
@Configuration
@ConfigurationProperties(prefix = "umdc.security.jwt")
public class JwtConfigProperties {
    private String secret;
    private long expirationMs;

    public JwtConfigProperties() {
        // Default Constructor
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    public void setExpirationMs(long expirationMs) {
        this.expirationMs = expirationMs;
    }
}
