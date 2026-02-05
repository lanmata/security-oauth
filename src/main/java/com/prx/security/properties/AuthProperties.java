package com.prx.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/// Represents the properties for authentication.
@Component
@ConfigurationProperties(prefix = "prx.auth")
public class AuthProperties {

    private List<ClientProperties> clients;

    public AuthProperties() {
        // Default constructor
    }

    public List<ClientProperties> getClients() {
        return clients;
    }

    public void setClients(List<ClientProperties> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "AuthProperties{" +
                "clients=" + clients +
                '}';
    }
}
