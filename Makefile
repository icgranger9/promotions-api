.PHONY: all clean test run compile package help

# Default target
all: help

# Compile the project
compile:
	@echo "Compiling project..."
	@mvn compile

# Run tests
test:
	@echo "Running tests..."
	@mvn test

# Clean the project (remove target directory)
clean:
	@echo "Cleaning project..."
	@mvn clean
	@rm -rf target/

# Package the project into a JAR
package:
	@echo "Packaging project..."
	@mvn -DskipTests=true package

# Run the application (development: uses spring-boot:run)
run:
	@echo "Running application (spring-boot:run)..."
	@mvn -DskipTests=true spring-boot:run

# Install dependencies (useful for first-time setup)
deps:
	@echo "Installing dependencies..."
	@mvn dependency:resolve

# Define a variable for the docker image
IMAGE_NAME = promotions-api

# Default target for 'make' command
docker-all: docker-build docker-run

Build the Docker Image
# This uses the multi-stage Dockerfile to create the final, production-ready image.
docker-build:
	@echo "🏗️ Building Docker image $(IMAGE_NAME)..."
	@docker build -t $(IMAGE_NAME):latest .

# Run the Docker Container
# This runs the image, mapping the application's port (8082) to the host.
# The '-d' flag runs it in detached mode (background).
docker-run:
	@echo "🚀 Running container from image $(IMAGE_NAME)..."
	@docker run -d --rm --name $(IMAGE_NAME)-container -p 8082:8082 $(IMAGE_NAME):latest

# Stop and Remove the Container
# A utility command to easily clean up the running container.
docker-stop:
	@echo "🛑 Stopping container $(IMAGE_NAME)-container..."
	@docker stop $(IMAGE_NAME)-container 2> /dev/null || true

# Clean up (Stop container and remove image)
# This removes both the running container and the built image.
docker-clean: docker-stop
	@echo "🗑️ Removing Docker image $(IMAGE_NAME)..."
	@docker rmi $(IMAGE_NAME):latest 2> /dev/null || true

# Display help information
help:
	@echo "Available targets:"
	@echo "  compile   - Compile the project"
	@echo "  test      - Run tests"
	@echo "  clean     - Clean build artifacts"
	@echo "  package   - Create JAR package (skips tests)"
	@echo "  run       - Run the app via spring-boot:run (skips tests)."
	@echo "              Can set SPRING_PROFILES_ACTIVE and JAVA_OPTS."
	@echo "  docker-all   - Build and run the Docker container"
	@echo "  docker-build - Build the Docker image"
	@echo "  docker-run   - Run the Docker container"
	@echo "  docker-stop  - Stop the running Docker container"
	@echo "  docker-clean - Stop container and remove Docker image"
	@echo "  deps      - Install dependencies"
	@echo "  help      - Show this help message"

# Set default goal
.DEFAULT_GOAL := help