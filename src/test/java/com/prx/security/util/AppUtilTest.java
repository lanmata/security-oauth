package com.prx.security.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUtilTest {

    @Test
    @DisplayName("isExcludePathValid returns false for null")
    void isExcludePathValidReturnsFalseForNull() {
        assertFalse(AppUtil.isExcludePathValid((String[]) null));
    }

    @Test
    @DisplayName("isExcludePathValid returns false for empty array")
    void isExcludePathValidReturnsFalseForEmptyArray() {
        assertFalse(AppUtil.isExcludePathValid());
    }

    @Test
    @DisplayName("isExcludePathValid returns false when first element is 'none'")
    void isExcludePathValidReturnsFalseForNone() {
        assertFalse(AppUtil.isExcludePathValid("none"));
        assertFalse(AppUtil.isExcludePathValid("NONE"));
    }

    @Test
    @DisplayName("isExcludePathValid returns true for valid paths")
    void isExcludePathValidReturnsTrueForValidPaths() {
        assertTrue(AppUtil.isExcludePathValid("/api/health"));
        assertTrue(AppUtil.isExcludePathValid("somePath", "other"));
    }
}

