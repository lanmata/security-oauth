package com.umdc.security.properties;

import com.umdc.security.to.AuthRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientPropertiesIdTest {

    @Test
    void idCanBeSetAndRead() {
        ClientProperties properties = new ClientProperties();
        properties.setId("client-1");
        assertEquals("client-1", properties.getId());
        assertTrue(properties.toString().contains("id='client-1'"));
    }

    @Test
    void authRequestToStringContainsAlias() {
        assertTrue(new AuthRequest("alias", "pwd").toString().contains("alias='alias'"));
    }
}
