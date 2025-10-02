FROM ubuntu:latest
LABEL authors="Umme Hani"

ENTRYPOINT ["top", "-b"]

# Use official Java 21 image
FROM openjdk:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy the jar file built by Gradle into the container
COPY build/libs/library-management-system-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (default Spring Boot port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
