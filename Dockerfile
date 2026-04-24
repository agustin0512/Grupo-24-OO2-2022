FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia primero el pom para cachear dependencias
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copia el resto del proyecto
COPY src ./src

# Build
RUN mvn clean package -DskipTests

# Imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

RUN mvn clean package -Dmaven.test.skip=true