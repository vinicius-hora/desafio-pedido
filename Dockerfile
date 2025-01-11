FROM maven:3.8-amazoncorretto-17 AS build

WORKDIR /workspace/app

COPY pom.xml .
RUN mvn verify clean --fail-never

COPY src src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine3.12

WORKDIR /app

COPY --from=build /workspace/app/target/pedidos.jar app.jar
COPY src/main/resources/application.yml application.yml

ENTRYPOINT ["java", "-DskipTests", "-jar", "-XX:MinRAMPercentage=20", "-XX:MaxRAMPercentage=90", "app.jar"]
