package com.umdc.security.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Registers {@link BackbonePublicClient} for any consuming application.
 * <p>
 * Consumers scope their own {@code @EnableFeignClients(basePackages = ...)} to
 * their own client package, so this library declares a second, independent
 * {@code @EnableFeignClients} for its own {@code com.umdc.security.client}
 * package. Spring Cloud OpenFeign composes multiple such declarations across a
 * context, so this is picked up automatically as long as the consumer's
 * {@code @SpringBootApplication(scanBasePackages = ...)} includes
 * {@code com.umdc.security} — already required for the rest of this library's
 * beans (see {@code SecurityConfig}, {@code AuthApiController}, etc.). No
 * per-consumer wiring needed.
 * </p>
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.cloud.openfeign.FeignClient")
@EnableFeignClients(basePackages = "com.umdc.security.client")
public class BackboneFeignClientConfig {

    public BackboneFeignClientConfig() {
        // Marker configuration — only exists to carry @EnableFeignClients.
    }
}
