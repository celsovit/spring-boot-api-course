FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

EXPOSE 8080

COPY target/course-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]