---
name: Pre Release Gate
description: Blocking release gate for tags or release branches in the security-oauth repository
trigger: pre-release
agents: [devops-engineer, code-reviewer, security-reviewer]
auto-block: true
---

# Pre Release Gate

## Trigger Conditions
- Event: release tag creation or push to `releases/*`
- Condition: release candidate for `security-oauth`

## Steps
1. Run `.github/prompts/prepare-release.prompt.md`.
2. Run `.github/prompts/review-code.prompt.md` on the release delta if code changed since the last release.
3. Run `.github/prompts/security-audit.prompt.md` for dependency, workflow, and security-sensitive code review.

## Fail Behavior
- Block release if build, PMD, coverage, publishing prerequisites, or critical security findings are unresolved.

## Output
- Release-gate summary with readiness, blockers, warnings, and required follow-up actions.

