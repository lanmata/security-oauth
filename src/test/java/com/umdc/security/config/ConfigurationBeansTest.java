package com.umdc.security.config;

import com.umdc.security.interceptor.SessionJwtInterceptor;
import com.umdc.security.jwt.JwtConverterProperties;
import com.umdc.security.properties.SecurityProperties;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConfigurationBeansTest {

    /** Exposes the registered interceptors for assertions. */
    private static final class TestRegistry extends InterceptorRegistry {
        List<Object> registered() {
            return getInterceptors();
        }
    }

    @Test
    void sessionJwtWebConfigurerRegistersTheInterceptor() {
        SessionJwtWebConfigurer configurer = new SessionJwtWebConfigurer(mock(SessionJwtInterceptor.class));
        ReflectionTestUtils.setField(configurer, "appPath", "/api");
        TestRegistry registry = new TestRegistry();

        configurer.addInterceptors(registry);

        assertEquals(1, registry.registered().size());
    }

    @Test
    void jwtDecoderIsBuiltFromTheJwkSetUri() {
        SecurityConfig config = new SecurityConfig(mock(SecurityProperties.class), mock(JwtConverterProperties.class));
        ReflectionTestUtils.setField(config, "jwkSetUri", "https://issuer.example.com/certs");

        JwtDecoder decoder = config.jwtDecoder(new RestTemplate());

        assertNotNull(decoder);
    }
}
