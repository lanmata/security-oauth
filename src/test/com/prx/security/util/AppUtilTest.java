package com.prx.security.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppUtilTest {

    @Test
    void testIsExcludePathValid() {
        assertTrue(AppUtil.isExcludePathValid("validPath"));
        assertFalse(AppUtil.isExcludePathValid("none"));
        assertFalse(AppUtil.isExcludePathValid());
        assertFalse(AppUtil.isExcludePathValid((String[]) null));
    }
}
