# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jre as runtime
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]
