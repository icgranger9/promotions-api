# --- STAGE 1: Build the application ---
# Use a Maven image that includes the Java 21 JDK for compilation
FROM maven:3.9.11-amazoncorretto-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and download dependencies (leverages Docker layer caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application into a JAR file
# '-DskipTests' is often used to speed up the container build, but remove it if you need tests to run
RUN mvn clean package -DskipTests

# --- STAGE 2: Create the final, lean runtime image ---
# Use a lightweight JRE base image for Java 21
FROM amazoncorretto:21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file from the 'build' stage
# The name of your JAR file will typically be 'your-artifact-name-version.jar'
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8082

# Define the entry point to run the application
# Use the 'java -jar' command to execute the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]