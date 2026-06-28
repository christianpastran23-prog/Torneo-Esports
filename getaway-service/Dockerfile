# Etapa 1: Compilar en la nube
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl getaway-service -am clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/getaway-service/target/*.jar app.jar

EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]