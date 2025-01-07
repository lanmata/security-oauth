# security-oauth

## Description
The `security-oauth` project is a comprehensive solution for implementing OAuth2-based authentication and authorization in Java applications. Built with Spring Boot, this project provides a robust and secure way to manage user authentication, authorization, and token management. It includes essential components such as user details, authentication requests, and responses, as well as services for handling authentication logic and generating JWT tokens.

## Features
- **OAuth2 Authentication**: Securely authenticate users using OAuth2 protocols.
- **JWT Token Management**: Generate and manage JSON Web Tokens (JWT) for authenticated sessions.
- **User Details Service**: Load and manage user details for authentication.
- **Configuration Properties**: Easily configure authentication properties through Spring Boot configuration.
- **Unit Tests**: Comprehensive unit tests to ensure the reliability and correctness of the authentication components.

## Installation
To install and run the `security-oauth` project, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://gitlab.com/prx-open/security-oauth.git
    cd security-oauth
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage
To use the `security-oauth` project in your application, follow these steps:

1. Configure the authentication properties in your `application.yml` file:
    ```yaml
    prx:
      auth:
        authorizationGrantType: authorization_code
        scope: read write
        clientSecret: your-client-secret
        clientId: your-client-id
        redirectUri: http://localhost
        username: your-username
        password: your-password
    ```

2. Implement the necessary authentication classes and services as described in the project documentation.

3. Run the application and test the authentication endpoints.

## Contributing
We welcome contributions to the `security-oauth` project. If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push them to your fork.
4. Create a pull request with a detailed description of your changes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Authors and Acknowledgments
This project is maintained by the PRX team. We would like to thank all contributors and users for their support and feedback.

## Project Status
The `security-oauth` project is actively maintained and updated. We are continuously working on adding new features and improving the existing ones. If you have any suggestions or issues, please feel free to open an issue on the GitLab repository.

## Quality Gate Status
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=lanmata_security-oauth)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
<br>
[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
<br>
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=bugs)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=lanmata_security-oauth&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=lanmata_security-oauth)
