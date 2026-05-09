# ===== BUILD STAGE =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build SIN tests (evita errores de SpringBootTest)
RUN mvn clean package -Dmaven.test.skip=true

# ===== RUNTIME STAGE =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Render usa PORT dinámico
ENV PORT=8080

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]