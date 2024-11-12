package com.prx.security.config;

import com.prx.security.interceptor.SessionJwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.prx.security.util.AppUtil.isExcludePathValid;

/**
 * SessionJwtWebConfigurer.
 *
 * @author Luis Antonio Mata
 * @version 1.0.0, 2024-11-10
 * @since 17
 */
@Configuration
public class SessionJwtWebConfigurer implements WebMvcConfigurer {

    private final SessionJwtInterceptor sessionJwtInterceptor;

    @Value("${app.api.endpoint}")
    private String appPath;

    @Value("${app.api.excludes}")
    private String[] appPathExcludes;

    public SessionJwtWebConfigurer(SessionJwtInterceptor sessionJwtInterceptor) {
        this.sessionJwtInterceptor = sessionJwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var interceptorRegistry = registry.addInterceptor(sessionJwtInterceptor)
                .addPathPatterns(appPath.concat("/**"));
        if(isExcludePathValid(appPath)) {
            interceptorRegistry.excludePathPatterns(appPathExcludes);
        }
    }
}

