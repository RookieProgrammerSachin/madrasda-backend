FROM maven:sapmachine AS build
LABEL maintainer="Adnan Mohamed Z"
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/MadrasdaAPI-0.0.1-RELEASE.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

