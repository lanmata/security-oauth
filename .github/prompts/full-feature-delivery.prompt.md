---
name: Full Feature Delivery
description: Coordinate end-to-end delivery of a non-trivial feature in the security-oauth repository using the project-specialized agents
mode: agent
agent: orchestrator
tools: [run_subagent, read_file, grep_search, file_search, run_in_terminal]
---

# Full Feature Delivery

## Input Variables
- `${featureSummary}`
- `${businessGoal}`
- `${affectedModules}`
- `${acceptanceCriteria}`
- `${deliveryConstraints}`

## Steps
1. Build a repository-grounded plan from `${featureSummary}` and `${affectedModules}`.
2. Delegate repository analysis when conventions or drift are unclear.
3. Delegate implementation, test writing, API review, code review, and security review as needed.
4. Ensure each agent follows the repository's Maven, PMD, JaCoCo, SonarCloud, and Qodana expectations.
5. Consolidate outputs into a single delivery summary.
6. Report open questions, risks, and validation status.

## Constraints
- Do not skip cross-agent review for public API or security-sensitive changes.
- Do not hide unresolved drift in versions, test layout, or configuration prefixes.
- Use the smallest set of agents that fully covers the request.

## Output Format
- Execution plan
- Delegated work summary
- Validation status
- Risks and follow-ups

