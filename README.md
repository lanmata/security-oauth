<div align="center">

# 🔐 UMDC Security OAuth

**Reusable OAuth2 / JWT security library for UMDC services** — session-token validation, JWT
conversion, backbone opaque-token introspection, and a ready-made authentication API contract.

[![Qodana](https://github.com/lanmata/security-oauth/actions/workflows/qodana_code_quality.yml/badge.svg?branch=main)](https://github.com/lanmata/security-oauth/actions/workflows/qodana_code_quality.yml)

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=lanmata-security-oauth)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)

[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=coverage)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Maintainability issues](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=software_quality_maintainability_issues)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Reliability issues](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=software_quality_reliability_issues)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
[![Security issues](https://sonarcloud.io/api/project_badges/measure?project=lanmata-security-oauth&metric=software_quality_security_issues)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)

<br/>

[![Java](https://img.shields.io/badge/Java-25%20LTS-blue?logo=java&style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?logo=spring&style=flat-square)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.3-brightgreen?logo=spring&style=flat-square)](https://spring.io/projects/spring-cloud)
[![Maven](https://img.shields.io/badge/Maven->=3.8-red?logo=apachemaven&style=flat-square)](https://maven.apache.org/)

[![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-blue?logo=mapstruct&style=flat-square)](https://mapstruct.org/)
[![JUnit](https://img.shields.io/badge/JUnit-6.1.3-red?logo=junit&style=flat-square)](https://junit.org/)
[![Mockito](https://img.shields.io/badge/Mockito-5.23.0-red?logo=mockito&style=flat-square)](https://site.mockito.org/)
[![JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.15-yellow?logo=jacoco&style=flat-square)](https://www.jacoco.org/jacoco/)

[![SonarCloud](https://img.shields.io/badge/SonarCloud-detected-4E9BCF?logo=sonarcloud&style=flat-square)](https://sonarcloud.io/)

</div>

Overview
--------
UMDC Security OAuth is a Java library (not a standalone application) that centralises the security
plumbing shared by UMDC services. A consuming Spring Boot service adds it as a dependency and gets:

- a `SecurityFilterChain` configured as an OAuth2 resource server (`SecurityConfig`);
- user **session-token** validation via `SessionJwtInterceptor` / `SessionJwtService`, with
  per-endpoint opt-out through `@SkipSessionValidation`;
- JWT conversion and keystore helpers (`JwtConverter`, `KeystoreUtil`);
- `BackboneOpaqueTokenIntrospector`, which validates backbone-issued M2M tokens by calling
  backbone-rest's introspection endpoint (authoritative for revocation) instead of verifying the
  signature locally;
- the `AuthAPi` contract (`/token`, `/session-token`) that services implement through `AuthService`.

Requirements
------------
Minimum requirements to build this library locally:
- Java 25 (JDK, LTS) or a compatible runtime (Amazon Corretto 25 recommended)
- Maven 3.8+
- Access to the private UMDC artifacts (`com.umdc:commons`, `com.umdc:commons-services`) hosted on the PRX Repsy repository (see `pom.xml`)

Quick build
-----------
```bash
# Fast syntax check (compile only, no tests)
mvn -q compile

# Full suite: PMD + JaCoCo + JUnit — the correctness/quality gate
mvn -DskipTests=false clean verify

# Run a single test class
mvn test -Dtest=BackboneOpaqueTokenIntrospectorTest

# Generate Javadoc
mvn javadoc:javadoc
```

The build enforces a minimum line coverage of 80% (JaCoCo, configured in `pom.xml`).

Usage
-----
Add the dependency to the consuming service:

```xml
<dependency>
    <groupId>com.umdc</groupId>
    <artifactId>security-oauth</artifactId>
    <version>0.0.3</version>
</dependency>
```

Configuration (`src/main/resources/application.yml` provides these defaults, fed from environment variables):

| Property | Environment variable | Purpose |
|---|---|---|
| `umdc.security.auth.converter.resource-id` | `AUTH_CLIENT_ID` | Resource/client id used by the JWT converter |
| `umdc.security.auth.converter.principal-claim-name` | `AUTH_RESOURCE_PRINCIPAL` | JWT claim used as the principal name |
| `umdc.security.jwt.secret` | `APP_TOKEN_SECRET` | Secret used to sign/verify session JWTs |
| `umdc.security.jwt.expirationMs` | `APP_TOKEN_EXPIRATION` | Session token lifetime in milliseconds |

Excluding an endpoint from session-token validation (e.g. login, or an M2M endpoint guarded by its
own opaque-token chain):

```java
@SkipSessionValidation("Login endpoints — callers can't hold a session token before it's issued")
public interface AuthAPi { /* ... */ }
```

Introspection failure semantics: an inactive/revoked token raises `BadOpaqueTokenException`
(authentication rejected); an unreachable or failing backbone raises `OAuth2IntrospectionException`
(still fail-closed, but classified as an outage rather than a bad credential).

Package structure
-----------------
| Package (`com.umdc.security.`) | Purpose |
|---|---|
| `annotation` | `@SkipSessionValidation` |
| `client` / `client.to` | Feign client and DTOs for backbone-rest (`BackbonePublicClient`, introspection request/response) |
| `config` | `SecurityConfig`, `SessionJwtWebConfigurer` |
| `constant` | Shared constants (e.g. session header name) |
| `controller` | `AuthAPi` contract and default `AuthApiController` |
| `exception` | `CertificateSecurityException`, `JwtConverterException` |
| `interceptor` | `SessionJwtInterceptor` |
| `introspection` | `BackboneOpaqueTokenIntrospector` |
| `jwt` | `JwtConverter` and its configuration properties |
| `properties` | Typed `@ConfigurationProperties` (auth, client, store, security, management) |
| `service` | `AuthService`, `SessionJwtService` |
| `to` | `AuthRequest`, `AuthResponse` |
| `util` | `AppUtil`, `KeystoreUtil` |

Known issues and workarounds
---------------------------
1. Dependency resolution fails for `com.umdc:commons` / `commons-services`
   - Symptom: Maven cannot download the private UMDC artifacts.
   - Workaround: Configure the PRX Repsy repository credentials in `~/.m2/settings.xml` (server id matching the repository in `pom.xml`).

2. Transitive dependency vulnerability warnings reported by IDE / scanning tools
   - Symptom: IDE or scanner warns about Jackson, Netty, Logback, BouncyCastle, etc.
   - Workaround: Versions are already overridden in `pom.xml` properties (see `CHANGELOG`). To inspect: `mvn dependency:tree -DoutputFile=dependency-tree.txt`.

3. Mockito self-attach warnings on newer JDKs
   - Symptom: "A Java agent has been loaded dynamically" appears during `mvn test`.
   - Workaround: Harmless today; add Mockito as an explicit agent in Surefire `argLine` if a future JDK disallows dynamic attach.

Continuous Integration
----------------------
Workflows live in `.github/workflows/`: `ci.yml` (build and test), `build.yml`, and
`qodana_code_quality.yml` (Qodana, thresholds in `qodana.yaml`). SonarCloud reads the JaCoCo XML
report (`sonar.coverage.jacoco.xmlReportPaths` in `pom.xml`).

## How to verify Sonar coverage locally

1) Generate the JaCoCo XML report:

```bash
mvn -U clean verify
```

2) Confirm the report exists at `target/site/jacoco/jacoco.xml`.

3) Run Sonar analysis (requires a token):

```bash
mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.token=<SONAR_TOKEN>
```

## Documentation

- `CHANGELOG` — project changelog and migration notes (Keep a Changelog / SemVer)
- `README-BUILD.md` — build instructions
- `BUILD_VALIDATION_REPORT.md` — build validation results
- Javadoc — `mvn javadoc:javadoc` (public Javadoc is written in English)

Tech stack and versions
-----------------------
| Technology | Version | Source |
|---|---:|---|
| Apache Commons Compress | 1.28.0 | `pom.xml` (<dependencyManagement>) |
| Apache POI (poi-ooxml) | 5.5.1 | `pom.xml` (<properties>) |
| ASM | 9.10.1 | `pom.xml` (<properties>) |
| Google Gson | 2.14.0 | `pom.xml` (<properties>) |
| JUnit Jupiter (junit-jupiter) | 6.1.3 | `pom.xml` (<properties>) |
| JaCoCo (jacoco-maven-plugin) | 0.8.15 | `pom.xml` (<properties>) |
| MapStruct | 1.6.3 | `pom.xml` (<properties>) |
| Maven (minimum) | >= 3.8.0 (recommended) | `README-BUILD.md` (Quick start) |
| Maven Surefire Plugin | 3.5.6 | `pom.xml` (<properties>) |
| Maven Javadoc Plugin | 3.12.0 | `pom.xml` (<properties>) |
| PMD Maven Plugin | 3.28.0 | `pom.xml` (<properties>) |
| UMDC internal modules (commons / commons-services) | 0.0.3 | `pom.xml` (<properties>) |
| Spring Boot | 4.1.1 | `pom.xml` (<parent> / <properties>) |
| Spring Cloud | 2025.1.3 | `pom.xml` (<properties>) |
| Spring Cloud Dependencies | 5.0.3 | `pom.xml` (<properties>) |
| Spring Security (starter-security / oauth2-resource-server) | managed by Spring Boot 4.1.1 | `pom.xml` (<parent>) |
| Jackson 2.x / Jackson 3 | 2.22.3 / 3.2.3 | `pom.xml` (<properties>) |
| Netty | 4.2.18.Final | `pom.xml` (<properties>) |
| Logback | 1.6.5 | `pom.xml` (<properties>) |
| BouncyCastle (bcprov-jdk18on) | 1.86 | `pom.xml` (<properties>) |
| Tomcat Embedded (tomcat-embed-core) | 11.0.26 | `pom.xml` (<properties>) |
| Mockito | 5.23.0 | `pom.xml` (<properties>) |
| commons-lang3 | 3.20.0 | `pom.xml` (<properties>) |
| Java (runtime / compile) | 25 | `pom.xml` (<properties> `java.version`) |

Note: "managed" means the version is controlled by the Spring Boot BOM rather than pinned in this project.

Files scanned
-------------
- `pom.xml` — project metadata, properties, dependencies, plugin versions
- `src/main/resources/application.yml` — default configuration properties
- `.github/workflows/` — CI pipelines (build, Qodana)
- `README-BUILD.md` — build instructions

Contributing
------------
- Keep all public Javadoc comments in English.
- Tests must carry `@DisplayName` on test methods (JUnit 6) to improve readability.
- Run `mvn clean verify` (PMD + JaCoCo ≥ 80% + tests) before pushing.

License
-------
Proprietary and confidential — Copyright (c) 2024-2026 UM Dev Creative. All Rights Reserved.
This is not open-source software. See `pom.xml` for project metadata.

More
----
For questions, reach out to:

<luis.antonio.mata@gmail.com>

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=lanmata-security-oauth)
