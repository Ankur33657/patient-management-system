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
#COPY --from=builder /app/target/patient-service-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 4000
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
#
#
#


FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service/pom.xml common-service/pom.xml
COPY patient-service/pom.xml patient-service/pom.xml

COPY common-service/src common-service/src
COPY patient-service/src patient-service/src

# Build common-service first
RUN mvn -f common-service/pom.xml clean install -DskipTests \
    || (echo "❌ common-service BUILD FAILED" && exit 1)

# Only runs if common-service succeeded
RUN mvn -f patient-service/pom.xml clean package -DskipTests \
    || (echo "❌ patient-service BUILD FAILED" && exit 1)


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /app/patient-service/target/patient-service-0.0.1-SNAPSHOT.jar \
    app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
