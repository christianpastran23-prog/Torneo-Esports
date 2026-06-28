
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace


COPY . .

# Compilar específicamente el módulo de usuarios saltándose los tests
RUN mvn -pl usuario -am clean package -DskipTests

# Etapa 2: Crear la imagen final de ejecución liviana
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el JAR generado en la etapa de compilación
COPY --from=build /workspace/usuario/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]