FROM amazoncorretto:17
LABEL maintainer="Adnan Mohamed Z"
ADD target/MadrasdaAPI-0.0.1-RELEASE.jar spring-madrasda.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-madrasda.jar"]