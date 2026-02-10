package com.prx.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorePropertiesTest {

    @Test
    void testGettersAndSetters() {
        StoreProperties storeProperties = new StoreProperties();

        storeProperties.setLocation("location");
        assertEquals("location", storeProperties.getLocation());

        storeProperties.setPassword("password");
        assertEquals("password", storeProperties.getPassword());

        storeProperties.setType("type");
        assertEquals("type", storeProperties.getType());
    }
}
