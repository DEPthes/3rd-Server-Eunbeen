# Stage 1: Build the application using Gradle with JDK 17
FROM openjdk:17-alpine as builder
WORKDIR /build

# Copy Gradle build files
COPY build.gradle settings.gradle /build/

# Download dependencies without running tests
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# Copy the rest of the application code
COPY . /build

# Build the application without running tests
RUN gradle build -x test --parallel

# Stage 2: Run the application using Amazon Corretto JDK 17
FROM amazoncorretto:17
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /build/build/libs/*.jar SpringAdvanced.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the JAR file with Java
ENTRYPOINT ["java", "-jar", "SpringAdvanced.jar"]