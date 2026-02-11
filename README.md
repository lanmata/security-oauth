# PRX Security OAuth
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=lanmata_security-oauth)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)

<!-- Tech badges -->
[![Java](https://img.shields.io/badge/Java-21-blue?logo=java&style=flat-square)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-%3E%3D%203.8.0-orange?logo=apachemaven&style=flat-square)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen?logo=spring&style=flat-square)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.1-lightgrey?logo=spring&style=flat-square)](https://spring.io/projects/spring-cloud)
[![JUnit](https://img.shields.io/badge/JUnit%20Jupiter-5.11.3-red?logo=junit5&style=flat-square)](https://junit.org/junit5/)
[![JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.14-yellow?logo=jacoco&style=flat-square)](https://www.jacoco.org/jacoco/)
[![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5.Final-blue?logo=mapstruct&style=flat-square)](https://mapstruct.org/)
[![Mockito](https://img.shields.io/badge/Mockito-5.21.0-lightgrey?logo=mockito&style=flat-square)](https://site.mockito.org/)

One-line summary: A small Java library providing reusable helpers and configuration for OAuth2 / JWT security used across PRX services.

Quick start
-----------
Prerequisites: JDK 21 and Maven 3.8+ installed on your system.

- Run the full build and tests (this project generates a JaCoCo XML report as part of the build):

```powershell
mvn -DskipTests=false clean verify
```

- Generate Javadoc:

```powershell
mvn javadoc:javadoc
```

- JaCoCo XML report (for CI and Sonar):

  - Default path: target/site/jacoco/jacoco.xml
  - The build enforces a minimum line coverage of 80% (configured in `pom.xml`).

Tech stack and versions (alphabetical)
-------------------------------------
The table below is generated from `pom.xml` and repository docs. When a version is available in the POM it is shown; otherwise a conservative fallback is used.

| Technology | Version | Source |
|---|---:|---|
| Apache Commons Compress | 1.21 | `pom.xml` (<dependencyManagement>) |
| Apache POI (poi-ooxml) | 5.2.4 | `pom.xml` (<properties>) |
| ASM | 9.7 | `pom.xml` (<properties>) |
| Google Gson | 2.10.1 | `pom.xml` (<properties>) |
| JUnit Jupiter (junit-jupiter) | 5.11.3 | `pom.xml` (<properties>) |
| JaCoCo (jacoco-maven-plugin) | 0.8.14 | `pom.xml` (<properties>) |
| MapStruct | 1.5.5.Final | `pom.xml` (<properties>) |
| Maven (minimum) | >= 3.8.0 (recommended) | `README-BUILD.md` (Quick start) |
| Maven Surefire Plugin | 3.5.2 | `pom.xml` (<properties>) |
| Maven Javadoc Plugin | 3.6.3 | `pom.xml` (<properties>) |
| PMD Maven Plugin | 3.28.0 | `pom.xml` (<properties>) |
| PRX internal modules (prx-commons / commons-services) | 0.0.1 | `pom.xml` (<properties>) |
| Spring Boot | 3.5.8 | `pom.xml` (<parent> / <properties>) |
| Spring Cloud | 2025.0.1 | `pom.xml` (<properties>) |
| Spring Cloud Dependencies | 4.2.0 | `pom.xml` (<properties>) |
| Spring Security OAuth2 Autoconfigure | 3.4.1 | `pom.xml` (<properties>) |
| Tomcat Embedded (tomcat-embed-core) | 11.0.15 | `pom.xml` (<properties>) |
| Mockito | 5.21.0 | `pom.xml` (<properties>) |
| commons-lang3 | 3.17.0 | `pom.xml` (<properties>) |
| Java (runtime / compile) | 21 | `pom.xml` (<properties> `java.version`) |

Notes and sources
-----------------
- All versions above were extracted from `pom.xml` properties, dependency management, or repository documentation (`README-BUILD.md`). See `pom.xml` in the repository root for the exact source definitions and plugin configuration.
- JaCoCo is configured to emit both XML and HTML reports during the `verify` phase; CI/Sonar reads `target/site/jacoco/jacoco.xml` (property `sonar.coverage.jacoco.xmlReportPaths` in `pom.xml`).
- The project sets `java.version` to 21 in `pom.xml` and uses it in the Maven Compiler and plugin configurations.

Contributing
------------
- Keep all public Javadoc comments in English.
- Tests should include `@DisplayName` on test methods (JUnit 5) to improve readability.

License and authors
-------------------
See `pom.xml` for project metadata and author contact information.

Build validation and further documentation
-----------------------------------------
- Build validation and build instructions are available in `BUILD_VALIDATION_REPORT.md` and `README-BUILD.md` (root).

End of README.
