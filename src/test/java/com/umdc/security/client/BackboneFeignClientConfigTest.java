package com.umdc.security.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BackboneFeignClientConfigTest {

    @Test
    @DisplayName("config class instantiates as a plain marker for @EnableFeignClients")
    void instantiates() {
        assertNotNull(new BackboneFeignClientConfig());
    }
}
