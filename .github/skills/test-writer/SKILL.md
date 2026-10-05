---
name: Test Writer Skills
description: Consolidated skill set — JUnit 5, Mockito, and JaCoCo coverage for security-oauth
applies-to: [Test Writer]
---

# Test Writer — Skill Definition

## 1. Project-Specific Patterns
- Tests are primarily JUnit Jupiter with Mockito-based mocking.
- The repo guidance explicitly asks for `@DisplayName` on test methods.
- Existing tests cover controllers, interceptors, DTOs, exceptions, properties, and JWT/service behavior.
- Coverage is enforced at `verify`: 80% line coverage for the bundle and 70% branch coverage per package.

## 2. Naming Conventions
- Prefer `src/test/java/com/<package>/.../<ClassName>Test.java`.
- Mirror the production symbol under test in the test class name.
- Use human-readable `@DisplayName` strings consistent with current tests.

## 3. Error Handling
- Assert explicit HTTP statuses, thrown exceptions, and fallback behaviors.
- When testing security code, verify `401` handling, headers, and exception paths.
- Prefer deterministic assertions over broad integration-only coverage.

## 4. Key Files
- `../../../src/test/java/com/umdc/security`
- `../../../src/test/java/com/umdc/security`
- `../../../src/test/java/com/umdc/security`
- `../../../src/test/java/com/umdc/security`
- `src/test/com/umdc/security/SecurityPropertiesTest.java`
- `README.md`
- `README-BUILD.md`

## 5. Constraints
- Do not introduce flaky tests or external-service dependencies.
- Do not ignore the repository's current package drift; note it when adding tests.
- Do not use slow integration tests when focused unit tests cover the behavior.

## 6. Checklist
- [ ] Add `@DisplayName` to new JUnit 5 tests
- [ ] Mock heavy collaborators where practical
- [ ] Cover normal, invalid, and edge paths
- [ ] Run the narrowest useful Maven test scope first
- [ ] Re-check coverage-sensitive paths after changes

