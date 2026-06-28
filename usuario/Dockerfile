# Etapa 1: Compilar la aplicación de forma independiente
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace

# 1. Copiamos el archivo pom.xml del microservicio
COPY ./usuario/pom.xml ./pom.xml

# 2. Truco para ignorar el POM padre si da problemas en la nube:
# Removemos la etiqueta <parent> del pom.xml temporal si sigue fallando,
# o simplemente descargamos las dependencias directas:
COPY ./usuario/src ./src

# 3. Compilamos el jar directamente
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]