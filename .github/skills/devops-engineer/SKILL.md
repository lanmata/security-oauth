---
name: DevOps Engineer Skills
description: Consolidated skill set — build, CI, reporting, and release operations for security-oauth
applies-to: [DevOps Engineer]
---

# DevOps Engineer — Skill Definition

## 1. Project-Specific Patterns
- Maven is the build system and source of truth for plugins, reports, coverage thresholds, and publish repositories.
- GitHub Actions runs `clean verify`, SonarCloud analysis, and Qodana scans.
- GitLab SAST still exists as an additional security pipeline.
- Publishing uses Repsy metadata in `pom.xml` and may require environment credentials.

## 2. Naming Conventions
- Workflow files live in `.github/workflows/`.
- Reports are expected under `target/surefire-reports/`, `target/site/jacoco/`, `target/pmd.*`, and `target/site/apidocs/`.
- Maven profiles and plugin IDs in `pom.xml` are the canonical command anchors.

## 3. Error Handling
- Build failures from PMD or JaCoCo are expected hard gates and should be preserved.
- CI summaries should clearly distinguish test, coverage, static-analysis, and publishing failures.
- Missing private dependencies or credentials must be surfaced as environment/setup issues.

## 4. Key Files
- `pom.xml`
- `README-BUILD.md`
- `.github/workflows/build.yml`
- `.github/workflows/ci.yml`
- `.github/workflows/qodana_code_quality.yml`
- `.gitlab-ci.yml`
- `qodana.yaml`

## 5. Constraints
- Do not add duplicate CI steps that already exist in Maven or workflows.
- Do not break JDK 21 compatibility.
- Do not assume PRX private repository credentials are available in every environment.

## 6. Checklist
- [ ] Confirm the exact Maven lifecycle step required
- [ ] Verify report paths and uploaded artifacts
- [ ] Check secrets and private-repo prerequisites
- [ ] Check GitHub Actions and GitLab pipeline consistency
- [ ] Document any release blockers or environment dependencies

