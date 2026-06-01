# Stage 1: Build the Maven application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src
# Compile and package the jar file, skipping tests for smooth cloud building
RUN mvn clean package -DskipTests

# Stage 2: Lightweight runtime environment
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copy the compiled executable jar from Stage 1
COPY --from=build /app/target/*.jar app.jar
# Expose the standard port (Render will override this dynamically)
EXPOSE 8080
# Execution command
ENTRYPOINT ["java", "-jar", "app.jar"]
