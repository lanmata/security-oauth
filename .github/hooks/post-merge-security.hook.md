---
name: Post Merge Security Sweep
description: Non-blocking security follow-up after merges that change dependencies or security-sensitive files
trigger: post-merge
agents: [security-reviewer]
auto-block: false
---

# Post Merge Security Sweep

## Trigger Conditions
- Event: merge or direct push to `develop`, `main`, or `master`
- Condition: `pom.xml`, `.github/workflows/**`, `.gitlab-ci.yml`, `qodana.yaml`, or `src/main/java/com/umdc/security/{config,interceptor,jwt,service,util}/**` changed

## Steps
1. Run `.github/prompts/security-audit.prompt.md` focused on the merged files and dependency delta.
2. If dependency versions changed, include CVE validation in the audit.

## Fail Behavior
- Do not block after merge.
- Create a follow-up report with severity and remediation priority.

## Output
- Post-merge security summary with dependency, config, and code observations.

