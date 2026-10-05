---
name: API Reviewer Skills
description: Consolidated skill set — auth endpoint contract review for security-oauth
applies-to: [API Reviewer]
---

# API Reviewer — Skill Definition

## 1. Project-Specific Patterns
- The library exposes auth operations through an interface-first contract in `AuthAPi` and an implementation in `AuthApiController`.
- Endpoints are POST-only and JSON-based under `/api/v1/auth`.
- Swagger annotations are declared directly on the interface methods.
- Controller logic delegates to `AuthService` and returns `ResponseEntity<AuthResponse>`.

## 2. Naming Conventions
- Endpoint methods currently use `accessToken` and `generateTokenSession`.
- Request and response DTOs are `AuthRequest` and `AuthResponse`.
- Header constant: `BACKBONE_SESSION_TOKEN`, value `session-token-bkd`.

## 3. Error Handling
- Validate documented statuses against actual implementation behavior.
- Check for mismatches between interface documentation and controller logic.
- Review how invalid session tokens map to `400` vs `401` semantics in adjacent code.

## 4. Key Files
- `src/main/java/com/umdc/security/controller/AuthAPi.java`
- `src/main/java/com/umdc/security/controller/AuthApiController.java`
- `src/main/java/com/umdc/security/service/AuthService.java`
- `src/main/java/com/umdc/security/to/AuthRequest.java`
- `src/main/java/com/umdc/security/to/AuthResponse.java`
- `../../../src/test/java/com/umdc/security`

## 5. Constraints
- Do not approve breaking API changes without an explicit versioning decision.
- Do not drift interface annotations away from implementation behavior.
- Do not overlook header requirements or media-type declarations.

## 6. Checklist
- [ ] Review paths, verbs, headers, and media types
- [ ] Review status-code behavior vs documentation
- [ ] Review DTO usage and null/blank edge cases
- [ ] Review compatibility impact for library consumers
- [ ] Summarize any contract drift found

