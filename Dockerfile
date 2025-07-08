FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/iot-controller-service.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
