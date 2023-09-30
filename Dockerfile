# Use the official image as a parent image.
FROM openjdk:17-jdk

# Set the working directory in the container to /app.
WORKDIR /app

# Copy the jar file into our app directory in our image
COPY ./target/techtest-0.0.1-SNAPSHOT.jar /app

# We specify that we expose port 8080 on the container
EXPOSE 8080

# The command that is run when the container starts
CMD ["java", "-jar", "/app/swapi-ms-0.0.1-SNAPSHOT.jar"]