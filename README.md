# Backend tech test

## Prerequisites

- Java 17
- Maven
- Docker

## Download

```bash
git glone https://github.com/IamFranP13/techtest.git
```

## Build and Packaging

The project uses Maven for dependency management and packaging. Navigate to the directory where the project resides and run the following command:

```bash
mvn clean package
```

This will generate the executable JAR file in the target directory.

## Creating Docker Image using Docker Compose

First, make sure that Docker and Docker Compose are running on your system. Then, execute the following command at the root of the project:
```bash
docker-compose up --build -d
```

This will build your image and run it in a new Docker container as defined in your docker-compose.yml file.

## Running application using Docker Compose

To run the application along with any other services defined in your docker-compose.yml, simply run:

```bash
docker-compose up
```
Your application will be accessible at http://localhost:8080.

## Accessing Swagger UI 
Once the application is running, you can access the Swagger UI and API Docs via:

http://localhost:8080/swagger-ui.html
