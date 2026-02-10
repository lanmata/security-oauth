package com.prx.security.interceptor;

import com.prx.security.service.SessionJwtService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.prx.security.constant.ConstantApp.SESSION_TOKEN_KEY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;

class SessionJwtInterceptorSignatureExceptionTest {

    @Test
    @DisplayName("preHandle returns false and sets UNAUTHORIZED when SignatureException is thrown")
    void preHandleSignatureExceptionSetsUnauthorized() throws Exception {
        // Arrange
        SessionJwtService sessionJwtService = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(sessionJwtService);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String token = "bad.token.value";
        when(request.getHeader(SESSION_TOKEN_KEY)).thenReturn(token);
        when(sessionJwtService.isValid(token)).thenThrow(new SignatureException("Invalid signature token"));

        // Act
        boolean result = interceptor.preHandle(request, response, new Object());

        // Assert
        assertFalse(result);
        verify(response).setStatus(401);
        verify(response).addHeader(eq("Message-ID"), eq("Invalid signature token"));
    }
}

