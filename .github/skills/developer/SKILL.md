---
name: Developer Skills
description: Consolidated skill set — Java 25, Maven, Spring Security, and library implementation
applies-to: [Developer]
---

# Developer — Skill Definition

## 1. Project-Specific Patterns
- Security configuration is centralized in `SecurityConfig` using `SecurityFilterChain`, stateless sessions, JWT resource server wiring, and `RestTemplate` SSL bundles.
- Auth HTTP behavior is defined first in the `AuthAPi` interface and then implemented by `AuthApiController`.
- Several service abstractions are interfaces with default methods (`AuthService`, `SessionJwtService`).
- Property-binding classes rely on `@ConfigurationProperties` and simple JavaBean getters/setters.

## 2. Naming Conventions
- Use `com.umdc.security.<area>` packages.
- Keep class names descriptive and framework-aligned: `*Properties`, `*Config`, `*Controller`, `*Service`, `*Util`, `*Exception`.
- Maintain Javadoc in English, including method-level docs on public APIs.

## 3. Error Handling
- Return `ResponseEntity` for controller/service default flows.
- Preserve checked exception use for certificate and converter failures.
- Avoid swallowing security exceptions without logging or contract-aware handling.

## 4. Key Files
- `src/main/java/com/umdc/security/config/SecurityConfig.java`
- `src/main/java/com/umdc/security/controller/AuthAPi.java`
- `src/main/java/com/umdc/security/controller/AuthApiController.java`
- `src/main/java/com/umdc/security/service/AuthService.java`
- `src/main/java/com/umdc/security/service/SessionJwtService.java`
- `src/main/java/com/umdc/security/jwt/JwtConverter.java`
- `src/main/java/com/umdc/security/util/KeystoreUtil.java`

## 5. Constraints
- Do not reformat unrelated files.
- Do not introduce application-only assumptions into this library module.
- Do not change property prefixes or package ownership silently when repository drift exists.

## 6. Checklist
- [ ] Read the target class and its tests before editing
- [ ] Preserve or improve Javadoc for public APIs
- [ ] Respect PMD-sensitive style and design rules
- [ ] Add or update tests when behavior changes
- [ ] Validate relevant files with Maven-based checks

