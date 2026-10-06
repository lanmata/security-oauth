---
name: Implement Feature
description: Implement a feature in the security-oauth library following project-specific Java, Spring Security, and quality-gate conventions
mode: agent
agent: developer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Implement Feature

## Input Variables
- `${featureSummary}`
- `${affectedArea}`
- `${targetFiles}`
- `${acceptanceCriteria}`
- `${constraints}`

## Steps
1. Read the target files and adjacent tests under `src/main/java/com/umdc/security/**` and `src/test/**`.
2. Trace any impacted contract or configuration surface, especially `AuthAPi`, `AuthApiController`, `SecurityConfig`, `SessionJwtInterceptor`, or `@ConfigurationProperties` classes.
3. Implement the smallest correct change that satisfies `${acceptanceCriteria}`.
4. Preserve Javadoc in English on public APIs and keep package naming under `com.umdc.security`.
5. Add or update tests when behavior changes.
6. Run the narrowest useful validation first, then the broader Maven command if needed.
7. Summarize changed files, validation results, and any repo drift discovered.

## Constraints
- Do not invent new architectural patterns when an existing one already fits.
- Do not silently normalize known drift in docs, package names, or property prefixes unless `${constraints}` explicitly requests that cleanup.
- Avoid unrelated reformatting.

## Output Format
- Summary
- Files changed
- Tests added or updated
- Validation run
- Risks or follow-ups

