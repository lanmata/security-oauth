# Skills Catalog

## Agent-Specific Skill Folders

| Agent | Skill File |
|---|---|
| `orchestrator` | `.github/skills/orchestrator/SKILL.md` |
| `developer` | `.github/skills/developer/SKILL.md` |
| `test-writer` | `.github/skills/test-writer/SKILL.md` |
| `code-reviewer` | `.github/skills/code-reviewer/SKILL.md` |
| `security-reviewer` | `.github/skills/security-reviewer/SKILL.md` |
| `devops-engineer` | `.github/skills/devops-engineer/SKILL.md` |
| `api-reviewer` | `.github/skills/api-reviewer/SKILL.md` |
| `repo-requirements-analyst` | `.github/skills/repo-requirements-analyst/SKILL.md` |

## Shared Skills

| File | Topic | Used By |
|---|---|---|
| `.github/skills/api-contracts.skill.md` | Auth endpoint contract conventions | `developer`, `test-writer`, `api-reviewer`, `code-reviewer` |
| `.github/skills/quality-gates.skill.md` | Maven, PMD, JaCoCo, SonarCloud, and Qodana expectations | `developer`, `test-writer`, `code-reviewer`, `devops-engineer` |

## Notes
- Shared skills exist only where multiple agents need the same repository-specific knowledge.
- Drift that remains unresolved in the repository is documented inside agent skills instead of being rewritten as a new standard.

