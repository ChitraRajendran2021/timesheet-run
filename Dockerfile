FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR /app
COPY pom.xml . 
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn clean package -Dmaven.test.skip=true
#fff
FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
COPY --from=builder /app/target/*.jar /app.jar
CMD ["java","-jar","app.jar"]