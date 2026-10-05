---
name: Pre Pull Request Gate
description: Blocking review gate for pull requests into main development branches in the security-oauth repository
trigger: pull-request-opened-or-updated
agents: [code-reviewer, test-writer, security-reviewer]
auto-block: true
---

# Pre Pull Request Gate

## Trigger Conditions
- Event: pull request opened, synchronized, or reopened
- Condition: target branch is `main`, `master`, or `develop`

## Steps
1. Run `.github/prompts/review-code.prompt.md` with the changed files.
2. If `src/main/java/**` or `src/test/**` changed, run `.github/prompts/write-unit-tests.prompt.md` or `.github/prompts/improve-coverage.prompt.md` when test coverage is insufficient.
3. If `pom.xml`, `src/main/resources/**`, `.github/workflows/**`, `qodana.yaml`, or security classes changed, run `.github/prompts/security-audit.prompt.md`.

## Fail Behavior
- Block merge on unresolved blocking findings from code review, missing regression coverage for changed behavior, or critical security findings.

## Output
- PR-ready summary with blocking findings, coverage concerns, and security notes.

