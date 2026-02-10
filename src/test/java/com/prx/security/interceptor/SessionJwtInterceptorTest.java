package com.prx.security.interceptor;

import com.prx.security.service.SessionJwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.method.HandlerMethod;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SessionJwtInterceptorTest {

    public static class DummyController {
        public void testMethod() {}
        public void other() {}
    }

    @Test
    @DisplayName("preHandle returns true when method excluded")
    void preHandleReturnsTrueWhenMethodExcluded() throws Exception {
        SessionJwtService service = mock(SessionJwtService.class);
        SessionJwtInterceptor interceptor = new SessionJwtInterceptor(service);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        var handler = new HandlerMethod(new DummyController(), DummyController.class.getMethod("testMethod"));

        // set methodsExcludes via reflection since it's injected by Spring
        var field = SessionJwtInterceptor.class.getDeclaredField("methodsExcludes");
        field.setAccessible(true);
        field.set(interceptor, new String[]{"testMethod"});

        assertTrue(interceptor.preHandle(req, resp, handler));
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
        verify(resp, never()).addHeader(eq("Message-ID"), eq("Token invalid."));
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
        verify(resp).addHeader(eq("Message-ID"), eq("Token invalid."));
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
        verify(resp).addHeader(eq("Message-ID"), eq("Token invalid."));
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
        verify(resp).addHeader(eq("Message-ID"), eq("Token invalid."));
    }
}
