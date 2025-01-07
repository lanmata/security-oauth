package com.prx.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorePropertiesTest {

    @Test
    @DisplayName("Should create StoreProperties with default constructor")
    void shouldCreateStorePropertiesWithDefaultConstructor() {
        StoreProperties storeProperties = new StoreProperties();
        assertNull(storeProperties.getLocation());
        assertNull(storeProperties.getPassword());
        assertNull(storeProperties.getType());
    }

    @Test
    @DisplayName("Should set and get location")
    void shouldSetAndGetLocation() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setLocation("location");
        assertEquals("location", storeProperties.getLocation());
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setPassword("password");
        assertEquals("password", storeProperties.getPassword());
    }

    @Test
    @DisplayName("Should set and get type")
    void shouldSetAndGetType() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setType("type");
        assertEquals("type", storeProperties.getType());
    }

    @Test
    @DisplayName("Should handle null location")
    void shouldHandleNullLocation() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setLocation(null);
        assertNull(storeProperties.getLocation());
    }

    @Test
    @DisplayName("Should handle null password")
    void shouldHandleNullPassword() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setPassword(null);
        assertNull(storeProperties.getPassword());
    }

    @Test
    @DisplayName("Should handle null type")
    void shouldHandleNullType() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setType(null);
        assertNull(storeProperties.getType());
    }
}
