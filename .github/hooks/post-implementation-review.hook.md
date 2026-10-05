---
name: Post Implementation Review
description: Non-blocking feedback hook for feature-branch pushes in the security-oauth repository
trigger: feature-branch-push
agents: [code-reviewer, api-reviewer]
auto-block: false
---

# Post Implementation Review

## Trigger Conditions
- Event: push to a branch that is not `main`, `master`, `develop`, or `releases/*`
- Condition: changed files include `src/main/java/**`, `src/test/**`, or controller/API files

## Steps
1. Run `.github/prompts/review-code.prompt.md` on the latest change set.
2. If `src/main/java/com/umdc/security/controller/**` changed, run `.github/prompts/review-api-contract.prompt.md`.

## Fail Behavior
- Warn only; do not block the branch.
- Surface follow-up actions for the next implementation iteration.

## Output
- Review summary comment with maintainability, contract, and test recommendations.

