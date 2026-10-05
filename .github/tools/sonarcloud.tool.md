---
name: SonarCloud Analysis Tool
description: Tool for repository code-quality analysis in the security-oauth project
type: terminal
command-prefix: mvn
used-by: [Orchestrator, Code Reviewer, Security Reviewer, DevOps Engineer]
---

# SonarCloud Analysis Tool

## Purpose
Run the same SonarCloud-oriented analysis path used by the GitHub Actions workflow.

## Available Commands

### Build and analyze
```bash
mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=lanmata-security-oauth
```

## Output Locations
- Maven console output
- SonarCloud dashboard for project key `lanmata-security-oauth`
- Coverage input from `target/site/jacoco/jacoco.xml`

## Notes
- Requires `SONAR_TOKEN` in CI or local environment.
- The GitHub workflow also provides `GITHUB_TOKEN` for PR metadata.
- Sonar exclusions are configured centrally in `pom.xml`.

