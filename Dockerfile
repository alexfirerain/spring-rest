FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/spring-rest-0.0.1-SNAPSHOT.jar spr.jar
ENTRYPOINT ["java", "-jar", "spr.jar"]
