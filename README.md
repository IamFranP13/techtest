# Tech Test GFT

Backend tech test

## Prerequisites

- Java 17
- Maven
- Docker

## Download

```bash
git glone https://github.com/IamFranP13/techtest.git
```

## Build and Packaging

The project uses Maven for dependency management and packaging due to its familiarity and the community support available for troubleshooting when needed.

To build the downloaded project, navigate to the directory where the project resides and run the following command:

```bash
mvn clean package
```

This will generate the executable JAR file in the target directory.

## Creating Docker Image

To create the Docker image of the application, first make sure Docker is running on your system, and then execute the following command at the root of the project:

```bash
docker build -t techtest-gft .
```

## Running application

To run the application in a Docker container, execute the following command:

```bash
docker run -d --name techtest-gft -p 8080:8080 techtest-gft
```
This will start a new Docker container named techtest-gft based on the techtest-gft image. Application will be accesible at http://localhost:8080

## Accessing Swagger UI 
Once the application is running, you can access the Swagger UI and API Docs via:

http://localhost:8080/swagger-ui.html
