---
name: Git Tool
description: Tool for inspecting repository history and staged changes in the security-oauth project
type: terminal
command-prefix: git
used-by: [Orchestrator, Developer, Test Writer, Code Reviewer, Security Reviewer, DevOps Engineer, API Reviewer, Repo Requirements Analyst]
---

# Git Tool

## Purpose
Inspect changed files, branch state, and diff scope when preparing reviews, hooks, or release checks.

## Available Commands

### Show working tree status
```bash
git --no-pager status --short
```

### Review changed files
```bash
git --no-pager diff --name-only
```

### Inspect a change set
```bash
git --no-pager diff --stat
```

## Output Locations
- Terminal output only

## Notes
- Use `--no-pager` in automation-friendly contexts.
- Prefer file-scoped diffs when review scope is narrow.
- Useful for hook file filters such as `pom.xml`, `src/main/java/**`, or `.github/workflows/**`.

