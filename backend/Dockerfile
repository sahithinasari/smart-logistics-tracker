# Dockerfile
FROM eclipse-temurin:17-jdk-alpine

# App directory
WORKDIR /app

# Copy project JAR
COPY target/smart-logistics-tracker.jar app.jar

# Expose port
EXPOSE 2045

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
