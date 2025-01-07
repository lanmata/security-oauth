package com.prx.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagementAuthenticatorPropertiesTest {

    @Test
    @DisplayName("Should create ManagementAuthenticatorProperties with default constructor")
    void shouldCreateManagementAuthenticatorPropertiesWithDefaultConstructor() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        assertNull(properties.getKeyAlias());
        assertNull(properties.getKeystore());
        assertNull(properties.getTruststore());
    }

    @Test
    @DisplayName("Should set and get key alias")
    void shouldSetAndGetKeyAlias() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        properties.setKeyAlias("keyAlias");
        assertEquals("keyAlias", properties.getKeyAlias());
    }

    @Test
    @DisplayName("Should set and get keystore properties")
    void shouldSetAndGetKeystoreProperties() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        StoreProperties keystore = new StoreProperties();
        properties.setKeystore(keystore);
        assertEquals(keystore, properties.getKeystore());
    }

    @Test
    @DisplayName("Should set and get truststore properties")
    void shouldSetAndGetTruststoreProperties() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        StoreProperties truststore = new StoreProperties();
        properties.setTruststore(truststore);
        assertEquals(truststore, properties.getTruststore());
    }

    @Test
    @DisplayName("Should handle null key alias")
    void shouldHandleNullKeyAlias() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        properties.setKeyAlias(null);
        assertNull(properties.getKeyAlias());
    }

    @Test
    @DisplayName("Should handle null keystore properties")
    void shouldHandleNullKeystoreProperties() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        properties.setKeystore(null);
        assertNull(properties.getKeystore());
    }

    @Test
    @DisplayName("Should handle null truststore properties")
    void shouldHandleNullTruststoreProperties() {
        ManagementAuthenticatorProperties properties = new ManagementAuthenticatorProperties();
        properties.setTruststore(null);
        assertNull(properties.getTruststore());
    }
}
