# Etapa 1 - Build: Usar a imagem Maven para compilar e empacotar a aplicação

FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

COPY . .

RUN mvn clean package


# Etapa 2 - Execução: Usar uma imagem OpenJRE (mais leve) para rodar o JAR

FROM openjdk:21-jdk-slim

COPY --from=build /target/course-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
