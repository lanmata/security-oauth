package com.prx.security.exception;

import com.prx.commons.exception.CertificateSecurityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateSecurityExceptionTest {

    @Test
    @DisplayName("Default constructor creates instance")
    void defaultConstructorCreatesInstance() {
        com.prx.commons.exception.CertificateSecurityException exception = new com.prx.commons.exception.CertificateSecurityException();
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Constructor with message creates instance with message")
    void constructorWithMessageCreatesInstanceWithMessage() {
        String message = "Error message";
        com.prx.commons.exception.CertificateSecurityException exception = new com.prx.commons.exception.CertificateSecurityException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with message and cause creates instance with message and cause")
    void constructorWithMessageAndCauseCreatesInstanceWithMessageAndCause() {
        String message = "Error message";
        Throwable cause = new Throwable("Cause");
        com.prx.commons.exception.CertificateSecurityException exception = new com.prx.commons.exception.CertificateSecurityException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Constructor with cause creates instance with cause")
    void constructorWithCauseCreatesInstanceWithCause() {
        Throwable cause = new Throwable("Cause");
        com.prx.commons.exception.CertificateSecurityException exception = new com.prx.commons.exception.CertificateSecurityException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Constructor with message, cause, suppression, and writable stack trace creates instance with all parameters")
    void constructorWithAllParametersCreatesInstanceWithAllParameters() {
        String message = "Error message";
        Throwable cause = new Throwable("Cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = false;
        com.prx.commons.exception.CertificateSecurityException exception = new CertificateSecurityException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.getStackTrace().length > 0);
    }
}
