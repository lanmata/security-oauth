---
name: Quality Gate Conventions
description: Shared — Maven, PMD, JaCoCo, SonarCloud, and Qodana expectations (Developer, Test Writer, Code Reviewer, DevOps Engineer)
applies-to: [Developer, Test Writer, Code Reviewer, DevOps Engineer]
---

# Quality Gate Conventions

## Scope
Shared guidance for build, test, static analysis, coverage, and CI validation in `security-oauth`.

## Project-Specific Notes
- `mvn clean verify` is the main verification path.
- PMD runs during `test` via `maven-pmd-plugin` and `ruleset.xml`.
- JaCoCo generates XML and HTML at `verify` and enforces 80% line / 70% branch thresholds.
- SonarCloud analysis is run from `.github/workflows/build.yml`.
- Qodana JVM analysis is configured by `qodana.yaml` and `.github/workflows/qodana_code_quality.yml`.

## Shared Checklist
- [ ] Maven command matches the intended scope
- [ ] PMD and coverage reports remain available at documented paths
- [ ] CI workflow impact assessed for changed files
- [ ] Failures are treated as gates unless explicitly downgraded

