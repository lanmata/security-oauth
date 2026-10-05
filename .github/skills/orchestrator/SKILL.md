---
name: Orchestrator Skills
description: Consolidated skill set — coordination for the Java/Spring Security security-oauth library
applies-to: [Orchestrator]
---

# Orchestrator — Skill Definition

## 1. Project-Specific Patterns
- Treat `security-oauth` as a shared library whose main code lives in `src/main/java/com/umdc/security/**`.
- Most feature work will span Spring Security config (`config/`), HTTP contract code (`controller/`), JWT helpers (`jwt/`, `service/`, `interceptor/`), and property classes (`properties/`).
- Quality gates are Maven-centric: `mvn clean verify` runs tests, PMD checks, and JaCoCo reporting/checks.
- CI also includes SonarCloud (`.github/workflows/build.yml`), Qodana (`.github/workflows/qodana_code_quality.yml`), and GitLab SAST (`.gitlab-ci.yml`).

## 2. Naming Conventions
- Production packages: `com.umdc.security.*`.
- Key classes: `SecurityConfig`, `AuthAPi`, `AuthApiController`, `SessionJwtInterceptor`, `SessionJwtService`, `JwtConverter`, `KeystoreUtil`.
- Test packages currently drift between `../../../src/test/java/com/umdc/security` and older `src/test/com/umdc/security/**`.

## 3. Error Handling
- Web-facing flows return `ResponseEntity` with statuses such as `200`, `400`, and `501`/`NOT_IMPLEMENTED` defaults.
- Checked exceptions are used for certificate and JWT-conversion concerns (`CertificateSecurityException`, `JwtConverterException`).
- Interceptor failures use HTTP `401` with a `Message-ID` header.

## 4. Key Files
- `pom.xml`
- `README.md`
- `README-BUILD.md`
- `ruleset.xml`
- `.github/workflows/build.yml`
- `.github/workflows/ci.yml`
- `.github/workflows/qodana_code_quality.yml`
- `src/main/java/com/umdc/security/config/SecurityConfig.java`
- `src/main/java/com/umdc/security/controller/AuthAPi.java`
- `src/main/resources/application.yml`

## 5. Constraints
- Do not assume README versions are authoritative when they conflict with `pom.xml`.
- Do not normalize package/config drift without explicitly calling it out.
- Do not skip validation against the repository's real Maven and CI gates.

## 6. Checklist
- [ ] Confirm requested work area in main code, tests, or CI
- [ ] Identify impacted contracts and configuration prefixes
- [ ] Delegate implementation, testing, API, security, or release work appropriately
- [ ] Validate against Maven and repository quality gates
- [ ] Report drift, risks, and follow-up items clearly

