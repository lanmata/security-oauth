package com.umdc.security.config;

import com.umdc.security.interceptor.SessionJwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Registers the {@link SessionJwtInterceptor} for the configured API path.
 * <p>
 * Per-endpoint exclusions are no longer handled here by path pattern — annotate
 * the endpoint itself with {@link com.umdc.security.annotation.SkipSessionValidation}
 * instead, which {@link SessionJwtInterceptor} checks directly. (The path-pattern
 * exclusion this configurer used to apply never actually worked: it checked
 * {@code isExcludePathValid(appPath)} — the single, always-set base path — instead
 * of {@code appPathExcludes}, so {@code excludePathPatterns} ran unconditionally
 * with whatever {@code umdc.api.excludes} held, "none" by default, which never
 * matches ­a real path anyway.)
 * </p>
 */
@Configuration
public class SessionJwtWebConfigurer implements WebMvcConfigurer {

    private final SessionJwtInterceptor sessionJwtInterceptor;

    @Value("${umdc.api.endpoint}")
    private String appPath;

    public SessionJwtWebConfigurer(SessionJwtInterceptor sessionJwtInterceptor) {
        this.sessionJwtInterceptor = sessionJwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionJwtInterceptor)
                .addPathPatterns(appPath.concat("/**"));
    }
}
