package com.prx.security.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthPropertiesTest {

    @Test
    @DisplayName("Should create AuthProperties with default constructor")
    void shouldCreateAuthPropertiesWithDefaultConstructor() {
        AuthProperties props = new AuthProperties();
        assertNull(props.getClients());
    }

    @Test
    @DisplayName("Should set and get clients list")
    void shouldSetAndGetClientsList() {
        AuthProperties props = new AuthProperties();
        ClientProperties client = new ClientProperties();
        client.setClientId("id1");
        props.setClients(List.of(client));
        assertNotNull(props.getClients());
        assertEquals(1, props.getClients().size());
        assertEquals("id1", props.getClients().get(0).getClientId());
    }

    @Test
    @DisplayName("toString includes clients")
    void toStringIncludesClients() {
        AuthProperties props = new AuthProperties();
        ClientProperties client = new ClientProperties();
        client.setClientId("id2");
        props.setClients(List.of(client));
        String s = props.toString();
        assertTrue(s.contains("clients="));
        assertTrue(s.contains("id2"));
    }
}

