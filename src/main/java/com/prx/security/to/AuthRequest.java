package com.prx.security.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an authentication request containing an alias and a password.
 * <p>
 * Validation annotations assert that both fields are not null/empty when used in a validation context.
 * </p>
 */
public record AuthRequest(
        @NotNull @NotEmpty @NotBlank
        String alias,
        @NotNull @NotEmpty @NotBlank
        String password) {

    /**
     * Returns a string representation of the AuthRequest.
     *
     * @return a string containing alias and password
     */
    @Override
    public String toString() {
        return "AuthRequest{" +
                "alias='" + alias + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
