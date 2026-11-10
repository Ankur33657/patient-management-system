FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service/pom.xml common-service/pom.xml
COPY auth-service/pom.xml auth-service/pom.xml

COPY common-service/src common-service/src
COPY auth-service/src auth-service/src

# Build common-service first
RUN mvn -f common-service/pom.xml clean install -DskipTests \
    || (echo "❌ common-service BUILD FAILED" && exit 1)

# Only runs if common-service succeeded
RUN mvn -f auth-service/pom.xml clean package -DskipTests \
    || (echo "❌ auth-service BUILD FAILED" && exit 1)


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar \
    app.jar

EXPOSE 4005

ENTRYPOINT ["java", "-jar", "app.jar"]