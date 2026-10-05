---
name: Security Audit
description: Audit security-relevant code, dependencies, config, and workflows in the security-oauth repository
mode: agent
agent: security-reviewer
tools: [read_file, grep_search, file_search, run_in_terminal, validate_cves, get_errors]
---

# Security Audit

## Input Variables
- `${auditScope}`
- `${dependencyChanges}`
- `${securityFiles}`
- `${releaseContext}`

## Steps
1. Review `${securityFiles}` and the affected Maven dependencies.
2. Validate dependency CVEs for changed or high-risk packages.
3. Inspect JWT processing, interceptor behavior, keystore/truststore handling, and environment-variable use.
4. Inspect CI security workflows if `${releaseContext}` or `${auditScope}` includes pipelines.
5. Classify findings by severity and exploitability.
6. Recommend remediations that preserve library behavior.

## Constraints
- Never expose secrets in the output.
- Do not recommend weakening auth, signature validation, or SSL handling for convenience.
- Call out repository drift where it affects risk assessment.

## Output Format
- Scope reviewed
- Dependency findings
- Code/config findings
- CI/workflow findings
- Recommended remediations

