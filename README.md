# Project Spring Boot with Unit Test using MockMvc

## Overview

A Spring Boot project with unit testing using MockMvc is an example of a Java application that uses the Spring Boot framework to create a RESTful web service and perform unit tests to verify the behavior of this service in RestController.

### Language and Tools

- **Language:** Java 17
- **Build:** Maven
- **Frameworks:**
    - Spring Boot 3.1.1
- **Database:** H2 (in memory)
- **Documentation API:** Swagger

## Configuration and Execution

### Configuration:

1. **Test:** Define environment variables in your test IDE on post 8080, such as `profile=test;`.
2. **Production:** Set production environment variables in your IDE on port 8443, such as `profile=prod;`.
3. **Docker:** Set test environment variables in your docker (test or production), such as `profile=test;`.
   
### Execution:

1. Run the command to install the dependencies (Note: as you will already be running the test environment, that is, the unit tests, you will need to place the environment variables together with the maven command): `mvn clean install -Dprofile=test `.
2. Start the service in your IDE by running the `SystemApplication` file (Note: follow the configuration step above to configure environment variables).
4. To run the Dockerfile, first generate the image, using the command `docker build -t imagem-docker-unit-test:tag -f Dockerfile .`.
5. After generating the image, you can run the docker image using the command `docker run -p 9090:8080 --name container-docker-unit-test -d imagem-docker-unit-test:tag`.

## Contributions

Contributions are welcome! Open issues, propose new features or send pull requests to improve the system.

## Contact

For more information or questions, please contact [rayanabonfanti@gmail.com].
