package com.prx.security.constant;

public enum SessionKey {
    IDENTIFIER("session-token");
    public final String value;

    SessionKey(String value) {
        this.value = value;
    }
}
