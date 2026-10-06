package com.umdc.security.interceptor;

import com.umdc.security.annotation.SkipSessionValidation;
import com.umdc.security.service.SessionJwtService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.method.HandlerMethod;

import static com.umdc.security.constant.ConstantApp.SESSION_TOKEN_KEY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SessionJwtInterceptorTest {

    public static class DummyController {
        @SkipSessionValidation
        public void testMethod() {
            // empty content
        }
        public void other() {
            // empty content
        }
    }

    @SkipSessionValidation("whole controller is M2M-only")
    public static class DummyAnnotatedTypeController {
        public void anyMethod() {
            // empty content
        }
    }

    @Test
    @DisplayName("preHandle returns true when method carries @SkipSessionValidation")
    void preHandleReturnsTrueWhenMethodAnnotated() throws Exception {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        var handler = new HandlerMethod(new DummyController(), DummyController.class.getMethod("testMethod"));

        assertTrue(interceptor.preHandle(req, resp, handler));
        verify(service, never()).isValid(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("preHandle returns true when the controller type carries @SkipSessionValidation")
    void preHandleReturnsTrueWhenTypeAnnotated() throws Exception {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        var handler = new HandlerMethod(new DummyAnnotatedTypeController(),
                DummyAnnotatedTypeController.class.getMethod("anyMethod"));

        assertTrue(interceptor.preHandle(req, resp, handler));
    }

    @Test
    @DisplayName("preHandle still enforces the session token for an unannotated method")
    void preHandleStillEnforcesTokenForUnannotatedMethod() throws Exception {
        SessionJwtService service = mock(SessionJwtService.class);
        when(service.isValid("badToken")).thenReturn(false);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getHeader(SESSION_TOKEN_KEY)).thenReturn("badToken");

        var handler = new HandlerMethod(new DummyController(), DummyController.class.getMethod("other"));

        assertFalse(interceptor.preHandle(req, resp, handler));
    }

    @Test
    @DisplayName("preHandle returns true when token is valid")
    void preHandleReturnsTrueWhenTokenValid() {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        String token = "validToken";
        when(req.getHeader("session-token")).thenReturn(token);
        when(service.isValid(token)).thenReturn(true);

        Object handler = new Object();

        assertTrue(interceptor.preHandle(req, resp, handler));
        verify(resp, never()).setStatus(anyInt());
        verify(resp, never()).addHeader("Message-ID", "Token invalid.");
    }

    @Test
    @DisplayName("preHandle returns false when token invalid")
    void preHandleReturnsFalseWhenTokenInvalid() {
        SessionJwtService service = mock(SessionJwtService.class);
        when(service.isValid("badToken")).thenReturn(false);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getHeader("session-token")).thenReturn("badToken");

        Object handler = new Object();

        assertFalse(interceptor.preHandle(req, resp, handler));
        verify(resp).setStatus(401);
        verify(resp).addHeader("Message-ID", "Token invalid.");
    }

    @Test
    @DisplayName("preHandle returns false when token is null")
    void preHandleReturnsFalseWhenTokenNull() {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getHeader("session-token")).thenReturn(null);

        Object handler = new Object();

        assertFalse(interceptor.preHandle(req, resp, handler));
        verify(resp).setStatus(401);
        verify(resp).addHeader("Message-ID", "Token invalid.");
    }

    @Test
    @DisplayName("preHandle returns false when token is blank")
    void preHandleReturnsFalseWhenTokenBlank() {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getHeader("session-token")).thenReturn("   ");

        Object handler = new Object();

        assertFalse(interceptor.preHandle(req, resp, handler));
        verify(resp).setStatus(401);
        verify(resp).addHeader("Message-ID", "Token invalid.");
    }

    @Test
    @DisplayName("preHandle returns false and sets UNAUTHORIZED when SignatureException is thrown")
    void preHandleSignatureExceptionSetsUnauthorized() {
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
        verify(response).addHeader("Message-ID", "Invalid signature token");
    }
}
