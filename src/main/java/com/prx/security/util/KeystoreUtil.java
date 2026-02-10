package com.prx.security.util;

import com.prx.security.exception.CertificateSecurityException;
import com.prx.security.properties.SecurityProperties;
import com.prx.security.properties.StoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundleKey;
import org.springframework.boot.ssl.SslStoreBundle;
import org.springframework.stereotype.Component;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/**
 * Utility class for handling keystore operations.
 * <p>
 * Provides methods to load a keystore from the classpath, print certificates and build SSL bundles/contexts
 * used by the application.
 * </p>
 *
 * @since 1.0
 */
@Component
public final class KeystoreUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeystoreUtil.class);

    /**
     * Default constructor.
     */
    public KeystoreUtil() {
        /* Default constructor for the KeyStoreUtil class. */
        LOGGER.info("KeyStoreUtil class instantiated.");
    }

    /**
     * Load a keystore from the classpath using the provided {@link StoreProperties}.
     *
     * @param securityProperties the store properties containing location, password and type
     * @return the loaded {@link KeyStore}
     * @throws CertificateSecurityException when a loading error occurs (I/O, certificate or keystore errors)
     */
    public KeyStore getKeyStore(StoreProperties securityProperties) throws CertificateSecurityException {
        KeyStore keyStore;
        // Load Truststore
        try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(securityProperties.getLocation())) {
            keyStore = KeyStore.getInstance(securityProperties.getType());
            keyStore.load(keyStoreStream, securityProperties.getPassword().toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
            LOGGER.warn("Error occurred while loading the trust store file.");
            throw new CertificateSecurityException(e);
        }
        return keyStore;
    }

    /**
     * Print certificates contained in the provided keystore.
     * <p>Logs basic certificate information for each alias.</p>
     *
     * @param keystore     the keystore to inspect
     * @param filename     the filename (used for logging)
     * @param isTrustStore true when the keystore is actually a truststore
     * @throws CertificateSecurityException when an error occurs while reading aliases
     */
    public void certificatePrint(KeyStore keystore, String filename, boolean isTrustStore) throws CertificateSecurityException {
        // Log the certificates in the truststore
        Enumeration<String> aliases;
        String from = isTrustStore ? "Truststore" : "Keystore";
        try {
            aliases = keystore.aliases();
            LOGGER.info("Loading values from {} as {}", filename, from);
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                Certificate cert = keystore.getCertificate(alias);
                LOGGER.info("""
                        --
                        Alias: {}
                        "Certificate: {}
                        """, alias, cert);
            }
        } catch (KeyStoreException e) {
            throw new CertificateSecurityException(e);
        }
    }

    /**
     * Build an {@link SSLContext} from the configured security properties.
     *
     * @param securityProperties the security configuration containing keystore and truststore info
     * @return the initialized {@link SSLContext}
     * @throws NoSuchAlgorithmException     when the requested algorithm is unavailable
     * @throws CertificateSecurityException when keystore loading fails
     * @throws UnrecoverableKeyException    when the key cannot be recovered
     * @throws KeyStoreException            when keystore operations fail
     * @throws KeyManagementException       when SSL context initialization fails
     */
    public SSLContext getSSLContext(SecurityProperties securityProperties) throws NoSuchAlgorithmException, CertificateSecurityException, UnrecoverableKeyException, KeyStoreException, KeyManagementException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        keyManagerFactory.init(getKeyStore(securityProperties.getKeystore()), securityProperties.getKeystore().getPassword().toCharArray());
        trustManagerFactory.init(getKeyStore(securityProperties.getTruststore()));

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        return sslContext;
    }

    /**
     * Build an {@link SslBundle} used by Spring from provided security properties.
     *
     * @param securityProperties the security configuration containing keystore and truststore info
     * @return the {@link SslBundle}
     * @throws CertificateSecurityException when keystore loading fails
     */
    public SslBundle getSslBundle(SecurityProperties securityProperties) throws CertificateSecurityException {
        return loadSslBundle(getKeyStore(securityProperties.getKeystore()),
                getKeyStore(securityProperties.getTruststore()),
                securityProperties.getKeystore().getPassword());
    }

    /**
     * Build an {@link SslBundle} for management authenticator using specific alias.
     *
     * @param securityProperties the security configuration containing management authenticator details
     * @return the {@link SslBundle}
     * @throws CertificateSecurityException when keystore loading fails
     */
    public SslBundle getManagementAuthenticatorSslBundle(SecurityProperties securityProperties) throws CertificateSecurityException {
        return loadSslBundle(getKeyStore(securityProperties.getManagementAuthenticator().getKeystore()),
                getKeyStore(securityProperties.getManagementAuthenticator().getTruststore()),
                securityProperties.getManagementAuthenticator().getKeystore().getPassword(),
                securityProperties.getManagementAuthenticator().getKeyAlias());
    }

    private SslBundle loadSslBundle(KeyStore trustStore, KeyStore keyStore, String keystorePassword) {
        return SslBundle.of(SslStoreBundle.of(keyStore, keystorePassword, trustStore));
    }

    private SslBundle loadSslBundle(KeyStore trustStore, KeyStore keyStore, String keystorePassword, String alias) {
        SslBundleKey bundleKey = SslBundleKey.of(keystorePassword, alias);
        return SslBundle.of(SslStoreBundle.of(keyStore, keystorePassword, trustStore), bundleKey);
    }

}
