package com.prx.security.interceptor;

import com.prx.security.SessionJwtService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.prx.security.constant.ConstantApp.SESSION_TOKEN_KEY;


/**
 * Interceptor to handle JWT session validation.
 */
@Component
public class SessionJwtInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionJwtInterceptor.class);

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
        String sessionToken = request.getHeader(SESSION_TOKEN_KEY);
        try {
            if (sessionToken == null || sessionToken.isBlank() || !sessionJwtService.isValid(sessionToken)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.addHeader("Message-ID", "Token invalid.");
                return false;
            }
        } catch (SignatureException e) {
            LOGGER.warn("Error occurred while JWT validation. {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.addHeader("Message-ID", e.getMessage());
            return false;
        }
        return true;
    }
}
