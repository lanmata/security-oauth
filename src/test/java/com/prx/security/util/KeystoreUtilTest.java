package com.prx.security.util;

import com.prx.security.properties.StoreProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeystoreUtilTest {

    private final KeystoreUtil keystoreUtil = new KeystoreUtil();

    @Test
    @DisplayName("getKeyStore returns KeyStore for missing resource (empty keystore)")
    void getKeyStoreReturnsKeyStoreForMissingResource() throws Exception {
        StoreProperties props = new StoreProperties();
        props.setLocation("non-existent-file.jks");
        props.setPassword("password");
        props.setType(KeyStore.getDefaultType());

        KeyStore ks = keystoreUtil.getKeyStore(props);
        assertNotNull(ks);
    }

    @Test
    @DisplayName("certificatePrint does not throw for empty keystore")
    void certificatePrintDoesNotThrowForEmptyKeystore() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        assertDoesNotThrow(() -> keystoreUtil.certificatePrint(ks, "test", true));
    }
}
