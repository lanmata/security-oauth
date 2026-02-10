package com.prx.security.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an authentication response that carries a generated token.
 *
 * @param token the generated authentication token
 */
public record AuthResponse(
        @NotNull @NotEmpty @NotBlank
        String token) {
}
