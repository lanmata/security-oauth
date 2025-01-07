package com.prx.security.exception;

import com.prx.commons.exception.JwtConverterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtConverterExceptionTest {

    @Test
    @DisplayName("Default constructor creates instance")
    void defaultConstructorCreatesInstance() {
        com.prx.commons.exception.JwtConverterException exception = new com.prx.commons.exception.JwtConverterException();
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Constructor with message creates instance with message")
    void constructorWithMessageCreatesInstanceWithMessage() {
        String message = "Error message";
        com.prx.commons.exception.JwtConverterException exception = new com.prx.commons.exception.JwtConverterException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with message and cause creates instance with message and cause")
    void constructorWithMessageAndCauseCreatesInstanceWithMessageAndCause() {
        String message = "Error message";
        Throwable cause = new Throwable("Cause");
        com.prx.commons.exception.JwtConverterException exception = new com.prx.commons.exception.JwtConverterException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Constructor with cause creates instance with cause")
    void constructorWithCauseCreatesInstanceWithCause() {
        Throwable cause = new Throwable("Cause");
        com.prx.commons.exception.JwtConverterException exception = new com.prx.commons.exception.JwtConverterException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Constructor with message, cause, suppression, and writable stack trace creates instance with all parameters")
    void constructorWithAllParametersCreatesInstanceWithAllParameters() {
        String message = "Error message";
        Throwable cause = new Throwable("Cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = false;
        com.prx.commons.exception.JwtConverterException exception = new JwtConverterException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.getStackTrace().length > 0);
    }
}
