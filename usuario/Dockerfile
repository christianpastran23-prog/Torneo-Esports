# Etapa 1: Compilar de forma directa y limpia
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace

# Como tus archivos están sueltos en la raíz en GitHub, los copiamos directo
COPY pom.xml ./pom.xml
COPY src ./src

# Compilamos el jar sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# El JAR se genera directamente en la carpeta target de la raíz
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]