---
name: Improve Coverage
description: Raise JaCoCo coverage in the security-oauth project while staying aligned with repository testing conventions
mode: agent
agent: test-writer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Improve Coverage

## Input Variables
- `${coverageReportPath}`
- `${lowCoverageClasses}`
- `${targetThreshold}`
- `${constraints}`

## Steps
1. Inspect `${coverageReportPath}` or the listed `${lowCoverageClasses}`.
2. Prioritize uncovered logic in security-sensitive and contract-sensitive code paths.
3. Add focused tests that improve branch and line coverage with minimal noise.
4. Re-run Maven verification or the relevant test subset.
5. Confirm that changes help satisfy `${targetThreshold}`.
6. Report which classes improved and which still need follow-up.

## Constraints
- Keep tests fast and maintainable.
- Avoid testing framework internals instead of project behavior.
- Call out if exclusions in `pom.xml` are the real reason a class remains outside coverage.

## Output Format
- Coverage targets
- Tests added or updated
- Validation run
- Remaining gaps

