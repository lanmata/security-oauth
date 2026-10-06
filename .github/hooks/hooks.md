# Hooks Catalog

| File | Trigger | Blocking | Agents |
|---|---|---:|---|
| `.github/hooks/pre-pull-request.hook.md` | PR opened or updated for `main`, `master`, or `develop` | Yes | `code-reviewer`, `test-writer`, `security-reviewer` |
| `.github/hooks/post-implementation-review.hook.md` | Push to feature branches | No | `code-reviewer`, `api-reviewer` |
| `.github/hooks/post-merge-security.hook.md` | Merge or direct push to mainline branches after dependency/security changes | No | `security-reviewer` |
| `.github/hooks/pre-release-gate.hook.md` | Release tag or `releases/*` branch | Yes | `devops-engineer`, `code-reviewer`, `security-reviewer` |

## Lifecycle Diagram

```text
feature branch push
  -> post-implementation-review (warn only)

pull request to mainline
  -> pre-pull-request (blocking)

merge with dependency/security changes
  -> post-merge-security (follow-up)

release tag / releases/*
  -> pre-release-gate (blocking)
```

