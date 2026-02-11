// README-BUILD.md - replace empty content with build guide
# Build & Test Guide (security-oauth)

This document describes how to build, run tests, generate coverage, and produce Javadoc for the `security-oauth` project on Windows (PowerShell).

## Prerequisites
- Java: JDK 21 installed and `JAVA_HOME` set
- Maven: 3.8.x or later installed and on the PATH
- Shell: PowerShell (commands shown below are PowerShell-compatible)
- Internet access to fetch dependencies (and access to the `PRX-Repsy` repository if using internal artifacts)

## Important environment variables
- `REPSY_ACCOUNT_USER` and `REPSY_ACCOUNT_PASSWORD` - used when publishing to the `repsy` repository. They are referenced in `pom.xml` as `${env.REPSY_ACCOUNT_USER}` and `${env.REPSY_ACCOUNT_PASSWORD}`.

## JaCoCo
- The project uses JaCoCo Maven plugin version 0.8.14 (explicitly set in `pom.xml`). Do not upgrade to 0.8.15 unless you confirm compatibility with your toolchain. The POM config generates both XML and HTML reports during the `verify` phase.
- XML report path (for CI/Sonar): `target/site/jacoco/jacoco.xml`

## Quick commands (PowerShell)
- Run unit tests only (fast):

    mvn -DskipTests=false -Dmaven.test.failure.ignore=false clean test

- Full build, static checks, and coverage (recommended):

    mvn -DskipTests=false clean verify

    Explanation: `verify` runs the `jacoco:report` and `jacoco:check` executions configured in the POM and will fail the build if coverage thresholds are not met. The JaCoCo report will be generated at `target/site/jacoco/jacoco.xml`.

- Generate Javadoc (aggregate):

    mvn javadoc:aggregate

- Run a single test class (example):

    mvn -Dtest=com.prx.security.jwt.JwtConverterTest test

## Where to find reports
- Surefire unit test reports: `target/surefire-reports/`
- JaCoCo HTML coverage: `target/site/jacoco/index.html`
- JaCoCo XML (for CI/Sonar): `target/site/jacoco/jacoco.xml`
- PMD: `target/pmd.xml` and `target/pmd.html`
- Javadoc: `target/site/apidocs/` (or `target/site/apidoc` depending on plugin)

## Coverage thresholds (as enforced in `pom.xml`)
- Line coverage: minimum 80% project-wide
- Branch coverage (per package): minimum 70% (configured in jacoco `check` rule)

## If coverage check fails
1. Inspect `target/site/jacoco/index.html` to identify low-coverage classes.
2. Add focused unit tests for small, deterministic paths (avoid slow integration tests to raise unit coverage quickly).
3. Re-run `mvn -DskipTests=false clean verify`.

## Notes about CI and private dependencies
- The POM references a private Repsy repository. If your environment requires credentials, set the following environment variables or configure Maven `settings.xml`:
  - `REPSY_ACCOUNT_USER` and `REPSY_ACCOUNT_PASSWORD` (properties used by the POM)

## Developer tips
- Prefer `Mockito` for mocking Claims or heavy objects in `SessionJwtService` and token-related tests.
- Use small, deterministic tests that run quickly so `mvn verify` remains fast in CI.

## Troubleshooting
If a build fails due to missing internal artifacts, ensure the `PRX-Repsy` repository credentials are valid or remove the dependency on `com.prx` artifacts while running locally.

## Contact
If you have questions about this build, contact the maintainer listed in `pom.xml`.

End of build guide.
