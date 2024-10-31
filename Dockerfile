FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/fourt-develop.jar /app/fourt-develop.jar

# Specify the command to run the application
CMD ["java", "-jar", "/app/fourt-develop.jar"]
