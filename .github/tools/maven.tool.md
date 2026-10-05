---
name: Maven Build Tool
description: Tool for building, packaging, verifying, and publishing the security-oauth project
type: terminal
command-prefix: mvn
used-by: [Orchestrator, Developer, Test Writer, Security Reviewer, DevOps Engineer, API Reviewer, Repo Requirements Analyst]
---

# Maven Build Tool

## Purpose
Provide the canonical commands for building and validating this Java 21 library from `pom.xml`.

## Available Commands

### Build and verify
```bash
mvn -DskipTests=false clean verify
```

### Fast test phase only
```bash
mvn -DskipTests=false -Dmaven.test.failure.ignore=false clean test
```

### Generate Javadoc
```bash
mvn javadoc:aggregate
```

### Publish to configured repository
```bash
mvn -DskipTests=false clean deploy
```

## Output Locations
- `target/`
- `target/surefire-reports/`
- `target/site/`

## Notes
- Requires JDK 21 and Maven 3.8+.
- Private dependencies and publishing may require access to `https://repo.repsy.io/mvn/lmata/prx`.
- Prefer `pom.xml` over README badges when versions disagree.

