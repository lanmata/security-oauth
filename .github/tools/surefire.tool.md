---
name: Maven Surefire Test Tool
description: Tool for running unit tests in the security-oauth project
type: terminal
command-prefix: mvn
used-by: [Orchestrator, Developer, Test Writer, API Reviewer, DevOps Engineer]
---

# Maven Surefire Test Tool

## Purpose
Run focused or full JUnit 5 test suites through Maven Surefire.

## Available Commands

### Run the full unit-test phase
```bash
mvn -DskipTests=false clean test
```

### Run one test class
```bash
mvn -Dtest=jwt.com.umdc.security.JwtConverterTest test
```

### Run one package-focused subset
```bash
mvn -Dtest='com.prx.security.controller.*Test' test
```

## Output Locations
- `target/surefire-reports/`

## Notes
- The repository mostly uses JUnit Jupiter and Mockito.
- New tests should prefer `src/test/java/**` and include `@DisplayName`.
- Some legacy tests still sit outside the standard Maven test package layout and should be treated carefully.

