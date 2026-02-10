package com.prx.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityPropertiesTest {

    @Test
    void testGettersAndSetters() {
        SecurityProperties securityProperties = new SecurityProperties();

        StoreProperties keystore = new StoreProperties();
        securityProperties.setKeystore(keystore);
        assertEquals(keystore, securityProperties.getKeystore());

        StoreProperties truststore = new StoreProperties();
        securityProperties.setTruststore(truststore);
        assertEquals(truststore, securityProperties.getTruststore());

        ManagementAuthenticatorProperties managementAuthenticator = new ManagementAuthenticatorProperties();
        securityProperties.setManagementAuthenticator(managementAuthenticator);
        assertEquals(managementAuthenticator, securityProperties.getManagementAuthenticator());
    }
}
