# Tools Catalog

| File | Used By | Key Commands |
|---|---|---|
| `.github/tools/maven.tool.md` | Most agents | `mvn -DskipTests=false clean verify`, `mvn clean deploy` |
| `.github/tools/surefire.tool.md` | Developer, Test Writer, API Reviewer, DevOps Engineer | `mvn -DskipTests=false clean test`, `mvn -Dtest=... test` |
| `.github/tools/pmd.tool.md` | Developer, Code Reviewer, DevOps Engineer | `mvn pmd:check pmd:cpd-check` |
| `.github/tools/jacoco.tool.md` | Developer, Test Writer, Code Reviewer, DevOps Engineer | `mvn -DskipTests=false clean verify`, `mvn -Pcoverage test jacoco:report` |
| `.github/tools/sonarcloud.tool.md` | Code Reviewer, Security Reviewer, DevOps Engineer | `mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ...` |
| `.github/tools/qodana.tool.md` | Code Reviewer, Security Reviewer, DevOps Engineer, Repo Requirements Analyst | `qodana scan` |
| `.github/tools/git.tool.md` | All agents | `git --no-pager status --short`, `git --no-pager diff --name-only` |
| `.github/tools/keytool.tool.md` | Security Reviewer, DevOps Engineer | `keytool -list -v -keystore ...` |

## Notes
- No Docker-specific tool was created because the repository currently has no `Dockerfile` or `docker-compose` file.
- No GitHub CLI tool was created because the repository evidence centers on Maven and CI workflows rather than `gh`-based release automation.

