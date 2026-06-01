# Stage 1: Build the Maven application with Java 25
FROM maven:3.9.6-eclipse-temurin-25 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Compile and package the jar file
RUN mvn clean package -DskipTests

# Stage 2: Runtime environment with Java 25
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app

# Copy the compiled executable jar from Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose the standard port
EXPOSE 8080

# Execution command
ENTRYPOINT ["java", "-jar", "app.jar"]
