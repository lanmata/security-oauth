---
name: Review Code
description: Review a change set in the security-oauth project for correctness, maintainability, API safety, and quality-gate compliance
mode: agent
agent: code-reviewer
tools: [read_file, grep_search, file_search, run_in_terminal, get_errors]
---

# Review Code

## Input Variables
- `${changeScope}`
- `${files}`
- `${reviewFocus}`
- `${baselineBranch}`

## Steps
1. Inspect `${files}` and compare them with `${baselineBranch}` when a diff is available.
2. Review correctness, PMD risk, coverage implications, and CI impact.
3. Check whether public APIs, configuration prefixes, or auth-contract behavior changed.
4. Flag any mismatch between code, tests, and repository docs.
5. Provide severity-ranked findings and suggested next actions.

## Constraints
- Keep findings specific and evidence-based.
- Distinguish definite defects from project drift or improvement suggestions.
- Treat this repository as a reusable library with downstream consumers.

## Output Format
- Blocking findings
- Non-blocking findings
- Quality-gate observations
- Recommended next steps

