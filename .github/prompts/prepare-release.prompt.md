---
name: Prepare Release
description: Prepare the security-oauth repository for a release by checking build, reports, publishing prerequisites, and release blockers
mode: agent
agent: devops-engineer
tools: [read_file, grep_search, file_search, apply_patch, create_file, run_in_terminal, get_errors]
---

# Prepare Release

## Input Variables
- `${releaseVersion}`
- `${releaseBranchOrTag}`
- `${releaseNotesScope}`
- `${environmentConstraints}`

## Steps
1. Verify the release context against `${releaseBranchOrTag}` and `${releaseVersion}`.
2. Check `pom.xml`, workflow files, and build docs for release readiness.
3. Run or plan the required build, test, PMD, and coverage gates.
4. Verify SonarCloud/Qodana expectations and artifact/report paths.
5. Verify publishing prerequisites for Repsy and any required environment variables.
6. Summarize blockers, warnings, and ready-to-release status.

## Constraints
- Do not claim a release is ready if private dependencies, secrets, or CI gates are unresolved.
- Preserve JDK 25 and current Maven lifecycle assumptions unless the release explicitly changes them.
- Keep release notes grounded in repository changes, not generic templates.

## Output Format
- Release readiness
- Required validations
- Publishing prerequisites
- Blockers and warnings

