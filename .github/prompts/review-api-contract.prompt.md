---
name: Review API Contract
description: Review an auth-contract change in the security-oauth repository for route, header, payload, and backward-compatibility safety
mode: agent
agent: api-reviewer
tools: [read_file, grep_search, file_search, run_in_terminal, get_errors]
---

# Review API Contract

## Input Variables
- `${apiFiles}`
- `${proposedChange}`
- `${compatibilityExpectation}`
- `${consumerImpact}`

## Steps
1. Read `${apiFiles}`, including controller interface, implementation, DTOs, and tests.
2. Compare annotations, documented responses, and actual behavior.
3. Check path, verb, header, media-type, and status-code implications of `${proposedChange}`.
4. Assess `${consumerImpact}` and backward-compatibility risk.
5. Recommend any test or documentation updates needed.

## Constraints
- Treat `AuthAPi` as a library-facing contract.
- Do not approve undocumented breaking changes.
- Distinguish implementation bugs from intentional contract changes.

## Output Format
- Contract summary
- Compatibility assessment
- Required follow-up actions

