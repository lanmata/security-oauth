# PRX Security OAuth

security-oauth is a small library that provides helpers and configuration for OAuth2/JWT security used across PRX services.

## What changed
- Dependency versions centralized in `pom.xml` properties to improve maintainability and simplify upgrades.
- Build documentation and validation reports added/updated.
- JaCoCo coverage enforcement configured (80% line coverage) and PMD checks integrated.

## Quick start
- Build and run tests:

    ```powershell
    mvn -DskipTests=false clean verify
    ```

- Generate Javadoc:

    ```powershell
    mvn javadoc:javadoc
    ```

## Documentation
- Build instructions: `README-BUILD.md`
- Build validation report: `BUILD_VALIDATION_REPORT.md`

## Contributing
Please keep Javadoc comments in English. New code should include JavaDoc for public classes, interfaces, enums and records.

## License and authors
See `pom.xml` for author contact information and project metadata.

End of README.
