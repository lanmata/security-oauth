# Agents Catalog

## Project Snapshot

| Field | Value |
|---|---|
| Name | `security-oauth` |
| Language | Java 25 |
| Build tool | Maven |
| Framework | Spring Boot 4.1.1 + Spring Security resource server |
| Test tool | JUnit Jupiter + Mockito via Maven Surefire |
| Lint/analysis | PMD, JaCoCo, SonarCloud, Qodana, GitLab SAST |
| Config system | Spring `@ConfigurationProperties` + `application.yml` + environment variables |
| Entry point | Library-style configuration and contract classes, especially `SecurityConfig` and `AuthAPi` |
| Domain modules | `config`, `controller`, `interceptor`, `jwt`, `properties`, `service`, `to`, `util` |
| Existing .github artifacts before bootstrap | `prompts/bootstrap-agent-infrastructure.prompt.md`, `workflows/build.yml`, `workflows/ci.yml`, `workflows/qodana_code_quality.yml` |

## Agents

| File | Agent | Invocable | Purpose |
|---|---|---:|---|
| `.github/agents/orchestrator.agent.md` | `orchestrator` | Yes | Coordinates multi-agent work across code, tests, API, security, and release activities |
| `.github/agents/developer.agent.md` | `developer` | Yes | Implements features and fixes in production code and related config |
| `.github/agents/test-writer.agent.md` | `test-writer` | Yes | Adds and updates JUnit 5 / Mockito tests and coverage improvements |
| `.github/agents/code-reviewer.agent.md` | `code-reviewer` | No | Reviews code against PMD, coverage, CI, and maintainability expectations |
| `.github/agents/security-reviewer.agent.md` | `security-reviewer` | No | Reviews CVEs, JWT/SSL handling, secrets, and security workflows |
| `.github/agents/devops-engineer.agent.md` | `devops-engineer` | No | Maintains build, CI, reporting, publishing, and release readiness |
| `.github/agents/api-reviewer.agent.md` | `api-reviewer` | No | Reviews controller and auth-contract changes for backward compatibility |
| `.github/agents/repo-requirements-analyst.agent.md` | `repo-requirements-analyst` | Yes | Produces repository-grounded requirement and drift analysis before implementation |

