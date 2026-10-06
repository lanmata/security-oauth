---
name: Security Reviewer Skills
description: Consolidated skill set — JWT, SSL, dependency, and configuration security review for security-oauth
applies-to: [Security Reviewer]
---

# Security Reviewer — Skill Definition

## 1. Project-Specific Patterns
- JWT decoding/authentication conversion centers on `SecurityConfig` and `JwtConverter`.
- Session token validation is handled by `SessionJwtInterceptor` and `SessionJwtService`.
- SSL client configuration uses `KeystoreUtil` plus Spring `SslBundle` abstractions.
- Dependency and code-quality scanners include SonarCloud, Qodana, and GitLab SAST.

## 2. Naming Conventions
- Security-related configuration classes live under `com.umdc.security.properties` and `com.umdc.security.jwt`.
- Environment-backed config appears in `src/main/resources/application.yml`.
- Custom checked exceptions use `*SecurityException` and `*ConverterException` naming.

## 3. Error Handling
- Inspect unauthorized flows (`401`) and token validation error headers.
- Review checked exceptions for certificate-loading and JWT conversion defects.
- Verify secrets remain environment-derived and never committed as literal values.

## 4. Key Files
- `pom.xml`
- `src/main/resources/application.yml`
- `src/main/java/com/umdc/security/config/SecurityConfig.java`
- `src/main/java/com/umdc/security/interceptor/SessionJwtInterceptor.java`
- `src/main/java/com/umdc/security/service/SessionJwtService.java`
- `src/main/java/com/umdc/security/util/KeystoreUtil.java`
- `.github/workflows/build.yml`
- `.github/workflows/qodana_code_quality.yml`
- `.gitlab-ci.yml`

## 5. Constraints
- Do not recommend disabling resource-server or SSL validation for convenience.
- Do not propose secret storage in source control.
- Do not ignore Maven dependency CVEs when safer compatible versions exist.

## 6. Checklist
- [ ] Review dependency versions and CVE status
- [ ] Review token, header, and unauthorized-response flows
- [ ] Review keystore/truststore handling and environment variables
- [ ] Review CI security-analysis coverage
- [ ] Report exploitability, impact, and remediation guidance

