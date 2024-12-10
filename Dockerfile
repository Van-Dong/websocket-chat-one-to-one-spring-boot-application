# Stage 1: build file .jar
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

# Stage 2
FROM amazoncorretto:17.0.2

WORKDIR /app/target
COPY --from=build /app/target/*.jar app.jar

ENV MONGODB_USERNAME='dongnv'
ENV MONGODB_PASSWORD='dongnv'
ENV MONGODB_PORT=27017
ENV MONGODB_HOST='10.1.37.113'
ENV MONGODB_DB='chat_app'

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]