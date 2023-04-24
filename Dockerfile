FROM amazoncorretto:17 AS build
LABEL maintainer="Adnan Mohamed Z"
FROM maven:sapmachine as mvn
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/
RUN mvn clean package -DskipTests
FROM amazoncorretto:17
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
