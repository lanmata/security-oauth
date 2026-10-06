---
name: Analyze Repository Requirements
description: Build a repository-grounded requirements snapshot before implementation or refactoring work in security-oauth
mode: agent
agent: repo-requirements-analyst
tools: [read_file, grep_search, file_search, create_file, apply_patch, run_in_terminal, get_errors]
---

# Analyze Repository Requirements

## Input Variables
- `${taskSummary}`
- `${suspectedAreas}`
- `${unknowns}`
- `${outputAudience}`

## Steps
1. Inspect the relevant source, test, build, and workflow files for `${taskSummary}`.
2. Confirm the real conventions for packages, config prefixes, tests, and CI gates.
3. Record drift or ambiguity that could affect implementation.
4. Summarize required files, risks, and validation paths for `${outputAudience}`.
5. Recommend which specialized agent should act next.

## Constraints
- Base conclusions only on repository evidence.
- Do not rewrite repository conventions into generic framework advice.
- Separate confirmed facts from open questions.

## Output Format
- Repository snapshot
- Confirmed conventions
- Risks and ambiguities
- Recommended next agent(s)

