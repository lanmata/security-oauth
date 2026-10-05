---
name: API Contract Conventions
description: Shared — auth endpoint contract and backward-compatibility review (Developer, Test Writer, API Reviewer, Code Reviewer)
applies-to: [Developer, Test Writer, API Reviewer, Code Reviewer]
---

# API Contract Conventions

## Scope
Shared guidance for the `/api/v1/auth` contract, including interface annotations in `AuthAPi`, implementation behavior in `AuthApiController`, request/response DTO usage, and header requirements.

## Project-Specific Notes
- Contract methods are defined on the interface and should stay aligned with the controller implementation.
- Requests and responses are JSON via `AuthRequest` and `AuthResponse`.
- The backbone-session header is `session-token-bkd`.
- Existing docs advertise `200`, `400`, and `406`; implementation review should confirm or flag mismatches.

## Shared Checklist
- [ ] Paths and HTTP verbs unchanged unless requested
- [ ] Header and media-type requirements preserved
- [ ] DTO and status-code behavior covered by tests
- [ ] Backward-compatibility impact documented

