---
name: Fix Lint Violations
description: Resolve PMD, SonarCloud, or Qodana findings in the security-oauth repository without changing intended behavior
mode: agent
agent: developer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Fix Lint Violations

## Input Variables
- `${findingSource}`
- `${files}`
- `${reportedIssues}`
- `${mustKeepBehavior}`

## Steps
1. Read the reported files and inspect the exact finding source (`PMD`, `SonarCloud`, or `Qodana`).
2. Confirm whether each issue is correctness-related, maintainability-related, or purely stylistic.
3. Fix violations in a way that preserves `${mustKeepBehavior}`.
4. Add or adjust tests if a refactor touches observable behavior.
5. Re-run the relevant analysis path or Maven phase.
6. Summarize which findings were fixed and which were intentionally left for follow-up.

## Constraints
- Respect `ruleset.xml` as the PMD source of truth.
- Avoid speculative cleanup unrelated to the reported findings.
- Keep library API signatures stable unless a finding proves the API itself is unsafe.

## Output Format
- Findings addressed
- Files changed
- Validation run
- Deferred items

