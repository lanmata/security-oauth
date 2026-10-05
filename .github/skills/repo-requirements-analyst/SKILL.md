---
name: Repo Requirements Analyst Skills
description: Consolidated skill set — repository discovery and convention analysis for security-oauth
applies-to: [Repo Requirements Analyst]
---

# Repo Requirements Analyst — Skill Definition

## 1. Project-Specific Patterns
- Source of truth for build/runtime versions is `pom.xml`, not necessarily `README.md`.
- The repo currently contains cross-cutting drift that should be documented before implementation: Spring Boot version mismatch in docs, property-prefix mismatch in `application.yml`, and test-package drift.
- The codebase mixes GitHub Actions, Qodana, SonarCloud, and GitLab SAST.

## 2. Naming Conventions
- Main package root: `com.umdc.security`.
- Test roots: both `../../../src/test/java/com/umdc/security` and `src/test/com/umdc/security/**`.
- Important module folders: `config`, `controller`, `interceptor`, `jwt`, `properties`, `service`, `util`.

## 3. Error Handling
- Requirements notes should distinguish confirmed conventions from suspected drift.
- Raise ambiguities where docs, config, and code disagree.
- Prefer evidence-backed findings over inferred standards.

## 4. Key Files
- `pom.xml`
- `README.md`
- `README-BUILD.md`
- `src/main/resources/application.yml`
- `.github/workflows/*`
- `.gitlab-ci.yml`
- `ruleset.xml`
- `qodana.yaml`

## 5. Constraints
- Do not invent missing architecture or workflow details.
- Do not recommend large cleanups as if they were already accepted standards.
- Do not hide repository inconsistencies from downstream agents.

## 6. Checklist
- [ ] Confirm stack, framework, build, and test tools
- [ ] Map main modules and entry contracts
- [ ] Record quality gates and CI integrations
- [ ] Record naming/config/version drift
- [ ] Produce a clear repository snapshot for follow-on work

