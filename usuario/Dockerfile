# Etapa 1: Compilar la aplicación posicionados en la raíz del proyecto completo
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace

# Copiamos TODOS los archivos (incluyendo el pom.xml padre de la raíz)
COPY . .

# Compilamos el módulo de usuario usando el pom.xml de la raíz como guía
RUN mvn clean package -pl usuario -am -DskipTests

# Etapa 2: Crear la imagen final de ejecución liviana
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el JAR que se generó dentro de la ruta del módulo correspondiente
COPY --from=build /workspace/usuario/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]