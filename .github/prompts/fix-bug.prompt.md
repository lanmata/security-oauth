---
name: Fix Bug
description: Diagnose and fix a defect in the security-oauth library while preserving public contract behavior and quality gates
mode: agent
agent: developer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Fix Bug

## Input Variables
- `${bugSummary}`
- `${symptoms}`
- `${suspectedFiles}`
- `${reproduction}`
- `${expectedBehavior}`

## Steps
1. Reproduce or reason through `${symptoms}` from the relevant Java and test files.
2. Trace the defect to the concrete source path instead of patching symptoms only.
3. Check whether the bug touches API semantics, JWT/security flows, or config binding.
4. Implement the minimal fix in production code.
5. Add or update regression tests that fail before the fix and pass after it.
6. Run focused Maven validation for the affected area.
7. Report root cause, fix scope, and any unresolved drift that may have contributed.

## Constraints
- Preserve backward compatibility unless `${expectedBehavior}` requires an intentional contract correction.
- Do not suppress exceptions or return codes just to make tests pass.
- Keep public Javadoc and method signatures coherent with actual behavior.

## Output Format
- Root cause
- Fix applied
- Regression coverage
- Validation run
- Remaining concerns

