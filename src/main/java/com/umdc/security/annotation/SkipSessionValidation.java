package com.umdc.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a controller method, or an entire controller/API interface, whose
 * requests must not be gated by {@link com.umdc.security.interceptor.SessionJwtInterceptor}'s
 * user session-token check.
 * <p>
 * Typical uses: a login endpoint that by definition cannot yet hold the
 * session token it is about to issue, or an M2M endpoint already gated by its
 * own backbone-opaque-token {@code SecurityFilterChain} (see
 * {@code BackboneOpaqueTokenIntrospector}) — an M2M caller only ever holds
 * that token, never a user session token.
 * </p>
 * <p>
 * Works whether the annotated method is a concrete controller method or an
 * interface's own method/type (the common interface-first REST pattern in
 * these codebases) — {@link com.umdc.security.interceptor.SessionJwtInterceptor}
 * resolves it via {@link org.springframework.core.annotation.AnnotationUtils#findAnnotation},
 * which searches the full class/interface hierarchy, not just the exact
 * {@link java.lang.reflect.Method} Spring MVC happened to resolve.
 * </p>
 * <p>
 * Replaces the older {@code umdc.api.exclude.methods} property (substring
 * matching against a handler method's name, resolved app-wide — a rename
 * silently drops the exclusion, and an unrelated method sharing a substring
 * silently gains one) and {@code umdc.api.excludes} (path patterns, which
 * additionally never worked correctly: {@code SecurityConfig}'s branch for it
 * had no terminal {@code .permitAll()}/{@code .authenticated()} call, and
 * {@code SessionJwtWebConfigurer} checked the wrong variable). Both are
 * compile-time invisible; this annotation is checked by the compiler, visible
 * at the declaration site, and immune to renames.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SkipSessionValidation {

    /**
     * Optional human-readable justification. Not read at runtime — purely for
     * the next person reading the annotated declaration.
     */
    String value() default "";
}
