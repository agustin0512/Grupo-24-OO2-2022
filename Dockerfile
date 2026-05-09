# === BUILD STAGE ===
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom y descargar dependencias primero (mejor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Build sin tests (más rápido en deploy)
RUN mvn clean package -DskipTests

# === RUNTIME STAGE ===
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar jar desde build
COPY --from=build /app/target/*.jar app.jar

# Puerto que usa Render
EXPOSE 8080

# Opciones JVM recomendadas para cloud
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Ejecutar aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]