---
name: Code Reviewer Skills
description: Consolidated skill set — static analysis, correctness, and maintainability review for security-oauth
applies-to: [Code Reviewer]
---

# Code Reviewer — Skill Definition

## 1. Project-Specific Patterns
- The repository enforces PMD rules from `ruleset.xml` during the Maven `test` phase.
- Coverage and report generation are handled by JaCoCo during `verify`.
- SonarCloud and Qodana are part of the expected review surface for PRs and mainline changes.
- Code is documentation-heavy; public-facing classes often include class and method Javadoc.

## 2. Naming Conventions
- Review production code in `com.umdc.security.*` and tests in both `com.prx.security.*` and legacy `com.umdc` locations.
- Watch for atypical names already present, such as `AuthAPi`, and avoid spreading inconsistent naming further.

## 3. Error Handling
- Confirm that controller, interceptor, and service layers expose predictable HTTP or exception behavior.
- Flag silent fallbacks that hide security or contract defects.
- Ensure checked exceptions remain meaningful and not over-broadened.

## 4. Key Files
- `ruleset.xml`
- `pom.xml`
- `.github/workflows/build.yml`
- `.github/workflows/ci.yml`
- `.github/workflows/qodana_code_quality.yml`
- `src/main/java/com/umdc/security/**`
- `src/test/**`

## 5. Constraints
- Do not request style changes that contradict the repository's current conventions.
- Do not approve quality-gate bypasses without an explicit repository-level rationale.
- Do not ignore mismatches between docs, POM metadata, and code structure.

## 6. Checklist
- [ ] Review PMD-sensitive areas and duplication risks
- [ ] Review test adequacy for changed code paths
- [ ] Check API and configuration backward compatibility
- [ ] Check CI/reporting implications
- [ ] Provide severity-ranked findings with file references

