---
name: Qodana JVM Tool
description: Tool for JetBrains static analysis in the security-oauth project
type: terminal
command-prefix: qodana
used-by: [Orchestrator, Code Reviewer, Security Reviewer, DevOps Engineer, Repo Requirements Analyst]
---

# Qodana JVM Tool

## Purpose
Run JetBrains Qodana analysis aligned with `qodana.yaml` and the repository workflow.

## Available Commands

### Run a local Qodana scan
```bash
qodana scan
```

### Use the GitHub Action configuration
```bash
# Executed by .github/workflows/qodana_code_quality.yml via JetBrains/qodana-action@v2025.3
```

## Output Locations
- Qodana local results directory (tool default)
- Qodana Cloud when configured through CI

## Notes
- The repository uses `jetbrains/qodana-jvm-community:2025.3` and `qodana.recommended` profile.
- CI requires `QODANA_TOKEN` and `QODANA_ENDPOINT`.
- Moderate and lower-severity thresholds are configured in `qodana.yaml`.

