FROM amazoncorretto:17 AS build
LABEL maintainer="Adnan Mohamed Z"

# Copy Maven wrapper files to the container
FROM maven:sapmachine as mvn
# Copy the project source code to the container
COPY src /app/src
COPY pom.xml /app/

# Set the working directory to the app directory
WORKDIR /app

# Build the project using the Maven wrapper
RUN mvn clean package -DskipTests

# Run stage
FROM amazoncorretto:17
# Copy the built JAR file to the container
EXPOSE 8080
# Run the application
CMD ["java", "-jar", "/app/target/MadrasdaAPI-0.0.1-RELEASE.jar"]
