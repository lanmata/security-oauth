package com.prx.security.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityPropertiesTest {

    @Test
    @DisplayName("Should create SecurityProperties with default constructor")
    void shouldCreateSecurityPropertiesWithDefaultConstructor() {
        SecurityProperties securityProperties = new SecurityProperties();
        assertNull(securityProperties.getKeystore());
        assertNull(securityProperties.getTruststore());
        assertNull(securityProperties.getManagementAuthenticator());
    }

    @Test
    @DisplayName("Should set and get keystore properties")
    void shouldSetAndGetKeystoreProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        StoreProperties keystore = new StoreProperties();
        securityProperties.setKeystore(keystore);
        assertEquals(keystore, securityProperties.getKeystore());
    }

    @Test
    @DisplayName("Should set and get truststore properties")
    void shouldSetAndGetTruststoreProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        StoreProperties truststore = new StoreProperties();
        securityProperties.setTruststore(truststore);
        assertEquals(truststore, securityProperties.getTruststore());
    }

    @Test
    @DisplayName("Should set and get management authenticator properties")
    void shouldSetAndGetManagementAuthenticatorProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        ManagementAuthenticatorProperties managementAuthenticator = new ManagementAuthenticatorProperties();
        securityProperties.setManagementAuthenticator(managementAuthenticator);
        assertEquals(managementAuthenticator, securityProperties.getManagementAuthenticator());
    }

    @Test
    @DisplayName("Should handle null keystore properties")
    void shouldHandleNullKeystoreProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setKeystore(null);
        assertNull(securityProperties.getKeystore());
    }

    @Test
    @DisplayName("Should handle null truststore properties")
    void shouldHandleNullTruststoreProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setTruststore(null);
        assertNull(securityProperties.getTruststore());
    }

    @Test
    @DisplayName("Should handle null management authenticator properties")
    void shouldHandleNullManagementAuthenticatorProperties() {
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setManagementAuthenticator(null);
        assertNull(securityProperties.getManagementAuthenticator());
    }
}
