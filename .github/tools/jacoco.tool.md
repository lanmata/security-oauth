---
name: JaCoCo Coverage Tool
description: Tool for generating and enforcing coverage reports in the security-oauth project
type: terminal
command-prefix: mvn
used-by: [Orchestrator, Developer, Test Writer, Code Reviewer, DevOps Engineer]
---

# JaCoCo Coverage Tool

## Purpose
Generate XML and HTML coverage reports and enforce the repository's coverage thresholds.

## Available Commands

### Run coverage through the normal verify lifecycle
```bash
mvn -DskipTests=false clean verify
```

### Run the dedicated coverage profile
```bash
mvn -Pcoverage test jacoco:report
```

## Output Locations
- `target/jacoco.exec`
- `target/site/jacoco/jacoco.xml`
- `target/site/jacoco/index.html`

## Notes
- The project enforces minimum coverage at `verify`: 80% line coverage for the bundle and 70% branch coverage per package.
- SonarCloud reads `target/site/jacoco/jacoco.xml`.
- The POM intentionally excludes some tooling and test-related classes from instrumentation.

