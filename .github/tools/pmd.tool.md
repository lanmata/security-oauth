---
name: PMD Static Analysis Tool
description: Tool for enforcing the custom Java ruleset used by the security-oauth project
type: terminal
command-prefix: mvn
used-by: [Orchestrator, Developer, Code Reviewer, DevOps Engineer]
---

# PMD Static Analysis Tool

## Purpose
Run the repository's custom PMD checks defined in `ruleset.xml`.

## Available Commands

### Run PMD checks explicitly
```bash
mvn pmd:check pmd:cpd-check
```

### Run PMD as part of normal validation
```bash
mvn -DskipTests=false clean test
```

## Output Locations
- `target/pmd.xml`
- `target/pmd.html`

## Notes
- The plugin is wired into the Maven `test` phase with `failOnViolation=true`.
- Rules come from the repository-local `ruleset.xml` file.
- PMD exclusions partially follow the `sonar.exclusions` property in `pom.xml`.

