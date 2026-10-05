---
name: Write Unit Tests
description: Add focused JUnit 5 and Mockito tests for code in the security-oauth library
mode: agent
agent: test-writer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Write Unit Tests

## Input Variables
- `${targetClass}`
- `${behaviorToCover}`
- `${existingTestFiles}`
- `${coverageGoal}`

## Steps
1. Read `${targetClass}` and any adjacent test files.
2. Mirror the production behavior under test with a focused test class or additions to an existing one.
3. Use JUnit 5 and Mockito patterns already present in the repository.
4. Add `@DisplayName` to each new test method.
5. Cover success, failure, and edge-case flows relevant to `${behaviorToCover}`.
6. Run the smallest useful Maven test scope.
7. Report the new coverage intent and any repository layout issues observed.

## Constraints
- Prefer deterministic unit tests over slow integration tests.
- Preserve existing package decisions unless the request explicitly includes cleanup.
- Avoid depending on external services or real secrets.

## Output Format
- Test scope
- New or updated test files
- Behaviors covered
- Validation run

