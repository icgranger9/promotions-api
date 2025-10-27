# Promotions API (Spring) — minimal

A small Maven-based Spring project demonstrating a promotions domain, a Spring bean/service layer, a REST controller, and unit tests.

## Overview
This project implements a minimal Promotions API (no Spring Boot extras required beyond what's in the POM). It includes:
- A Promotion entity and enums for status/reward type
- A PromotionRepository (Spring Data JPA)
- A PromotionService implementation
- A PromotionsController REST endpoint
- Unit tests (JUnit 5 + Mockito) and a test suite

## Repo structure (key files)
- src/main/java/com/example/promotions/
  - Application.java — Spring Boot entry point
  - configuration/AppConfig.java — bean configuration
  - controller/PromotionsController.java — REST controller
  - repository/PromotionRepository.java — JPA repository
  - service/PromotionService.java — service interface
  - service/impl/PromotionServiceImpl.java — service implementation
  - model/Promotion.java — entity
  - model/PromotionStatus.java, model/RewardType.java — enums
- src/test/java/com/example/promotions/ — unit tests and suite
- pom.xml — Maven configuration (targets Java 21 by default)
- Makefile — convenient wrappers for common Maven tasks

## Makefile commands
Run these from the project root.

- `make help`
  - Show available make targets.

- `make test`
  - Equivalent to `mvn clean test`. Runs the unit test suite.

- `make clean`
  - Runs `mvn clean` to remove build artifacts.

- `make compile`
  - Runs `mvn -DskipTests=true compile` to compile sources.

- `make package`
  - Runs `mvn -DskipTests=true package` to build the JAR.

- `make run`
  - Runs the application via `mvn -DskipTests=true spring-boot:run` (if present).

- `make deps`
  - Pre-download dependencies (`mvn dependency:resolve`).

Example:
```bash
# run the full test suite
make test

# or directly with maven
mvn clean test
```

## Testing
- Unit tests live under src/test/java and include a package-level suite (AllPromotionsSuite) that runs all promotion tests.
- Tests use JUnit 5 and Mockito.

## Java / Maven notes
- The project POM targets Java 21. Ensure Maven is running under JDK 21:
```bash
java --version
javac --version
mvn -v   # shows which JDK Maven runs with
```
- If `mvn -v` shows an older JDK, run:
```bash
export JAVA_HOME=/path/to/java-21
export PATH="$JAVA_HOME/bin:$PATH"
mvn -v
```

## Logging
- SLF4J API is added to the POM; Logback is the runtime binding included for console logging during tests and runtime.

## Running locally
1. Ensure JDK 21 and Maven are available (see above).
2. From project root:
```bash
make run
# or
mvn spring-boot:run
```
