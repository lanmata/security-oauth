package com.prx.security.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/// Represents an authentication request with alias and password.
public record AuthRequest(
        @NotNull @NotEmpty @NotBlank
        String alias,
        @NotNull @NotEmpty @NotBlank
        String password) {

    /// String representation of the object.
    @Override
    public String toString() {
        return "AuthRequest{" +
                "alias='" + alias + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
