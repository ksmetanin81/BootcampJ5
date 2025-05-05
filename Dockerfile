FROM openjdk:17-slim

COPY ./target/j5-1.0.0.jar /app/
WORKDIR /app

CMD ["java", "-jar", "j5-1.0.0.jar"]