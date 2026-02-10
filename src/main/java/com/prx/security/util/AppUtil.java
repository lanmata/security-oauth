package com.prx.security.util;

import java.util.Objects;

/**
 * Small application utilities used across the module.
 */
public final class AppUtil {
    private AppUtil() {
        //Default constructor
    }

    /**
     * Validates if the provided paths are not null, not empty, and the first path is not "none".
     *
     * @param paths the paths to validate
     * @return true if the paths are valid, false otherwise
     */
     public static boolean isExcludePathValid(String... paths) {
         return Objects.nonNull(paths) && paths.length > 0 && !paths[0].equalsIgnoreCase("none");
     }
}
