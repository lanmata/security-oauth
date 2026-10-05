# Copilot Agents — Master Index

This repository now includes a project-adapted agent infrastructure for the `security-oauth` Java library.

## Where to Start
- Agents: `.github/agents/agents.md`
- Skills: `.github/skills/skills.md`
- Tools: `.github/tools/tools.md`
- Prompts: `.github/prompts/prompts.md`
- Hooks: `.github/hooks/hooks.md`

## Recommended Entry Points
- Repository discovery or convention confirmation: `.github/prompts/analyze-repo-requirements.prompt.md`
- Single feature or bug: `.github/prompts/implement-feature.prompt.md` or `.github/prompts/fix-bug.prompt.md`
- Full multi-agent delivery: `.github/prompts/full-feature-delivery.prompt.md`
- Security or release work: `.github/prompts/security-audit.prompt.md`, `.github/prompts/prepare-release.prompt.md`

## Project-Specific Realities Captured by This Infrastructure
- Java 25 + Maven + Spring Security resource server library
- Auth contract centered on `AuthAPi` and `AuthApiController`
- JWT, interceptor, and keystore-sensitive areas highlighted for security review
- PMD, JaCoCo, SonarCloud, Qodana, and GitLab SAST captured as quality/security gates
- Existing repository drift explicitly documented rather than silently normalized

## Known Drift Tracked by the Infrastructure
- `README.md` advertises versions that do not fully match `pom.xml`
- `application.yml` uses `prx.*` keys while property classes bind to `umdc.security.*`
- Tests are split between `com.prx` and older `com.umdc` locations

## Validation Anchors
- Build: `mvn -DskipTests=false clean verify`
- SonarCloud: `.github/workflows/build.yml`
- Qodana: `.github/workflows/qodana_code_quality.yml`
- Security fallback: `.gitlab-ci.yml`

