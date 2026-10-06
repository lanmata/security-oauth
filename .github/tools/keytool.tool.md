---
name: Keytool Certificate Tool
description: Tool for inspecting Java keystores and truststores relevant to the security-oauth project
type: terminal
command-prefix: keytool
used-by: [Security Reviewer, DevOps Engineer]
---

# Keytool Certificate Tool

## Purpose
Inspect JKS/PKCS12 keystore and truststore contents that feed `KeystoreUtil` and Spring SSL bundle configuration.

## Available Commands

### List keystore entries
```bash
keytool -list -v -keystore path/to/keystore.jks
```

### Export one certificate
```bash
keytool -exportcert -rfc -alias <alias> -keystore path/to/keystore.jks -file certificate.pem
```

## Output Locations
- Terminal output
- Exported certificate files in the chosen destination path

## Notes
- Supply passwords interactively or through a secure runtime mechanism; never commit them.
- Use this when validating keystore aliases or certificate presence for `SecurityProperties` and management-authenticator settings.
- The repository does not ship actual keystore files, so usage is environment-specific.

