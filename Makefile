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
	@mvn package

# Run the application (we'll use exec:java since this is a simple bean demo)
run:
	@echo "Running application..."
	@mvn exec:java -Dexec.mainClass="com.example.hellobean.AppConfig"

# Install dependencies (useful for first-time setup)
deps:
	@echo "Installing dependencies..."
	@mvn dependency:resolve

# Display help information
help:
	@echo "Available targets:"
	@echo "  compile  - Compile the project"
	@echo "  test     - Run tests"
	@echo "  clean    - Clean build artifacts"
	@echo "  package  - Create JAR package"
	@echo "  run      - Run the application"
	@echo "  deps     - Install dependencies"
	@echo "  help     - Show this help message"

# Set default goal
.DEFAULT_GOAL := help