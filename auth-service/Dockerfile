## =========================
## Build stage
## =========================
#FROM maven:3.9.9-eclipse-temurin-21 AS builder
#
#WORKDIR /app
#
## Copy pom first for Docker layer caching
#COPY pom.xml .
#
#RUN mvn dependency:go-offline -B
#
## Copy source code
#COPY src ./src
#
## Build application
#RUN mvn clean package -DskipTests
#
#
## =========================
## Runtime stage
## =========================
#FROM eclipse-temurin:21-jre
#
#WORKDIR /app
#
#COPY --from=builder /app/target/auth-service-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 4005
#
#ENTRYPOINT ["java", "-jar", "app.jar"]


FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service/pom.xml common-service/pom.xml
COPY common-service/src common-service/src

COPY auth-service/pom.xml auth-service/pom.xml
COPY auth-service/src auth-service/src

RUN mvn -f common-service/pom.xml clean install -DskipTests \
    && mvn -f auth-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar \
    app.jar

EXPOSE 4005

ENTRYPOINT ["java", "-jar", "app.jar"]