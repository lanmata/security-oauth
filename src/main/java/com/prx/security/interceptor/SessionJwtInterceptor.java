package com.prx.security.interceptor;

import com.prx.security.SessionJwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.prx.security.constant.SessionKey.IDENTIFIER;


/**
 * Interceptor to handle JWT session validation.
 */
@Component
public class SessionJwtInterceptor implements HandlerInterceptor {

    private final SessionJwtService sessionJwtService;

    /**
     * Constructor to initialize SessionJwtInterceptor with SessionJwtService.
     *
     * @param sessionJwtService the service to handle JWT operations
     */
    public SessionJwtInterceptor(SessionJwtService sessionJwtService) {
        this.sessionJwtService = sessionJwtService;
    }

    /**
     * Pre-handle method to intercept requests and validate the session token.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param handler  the handler
     * @return true if the session token is valid, false otherwise
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String sessionToken = request.getHeader(IDENTIFIER.value);
        if (sessionToken == null || !IDENTIFIER.value.equals(sessionJwtService.getTokenClaims(sessionToken).get("type"))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.addHeader("Session", "Token invalid.");
            return false;
        }
        return true;
    }
}
