FROM zenika/alpine-maven:3-jdk8 AS build
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
RUN mvn package

FROM devexdev/8-jdk-alpine
WORKDIR /app/
COPY --from=build /build/target/investigation-development-2.0.0-SNAPSHOT.war /app
EXPOSE 3001

ENTRYPOINT ["java", "-jar", "-DHOST_URL=${HOST_URL}", "/app/investigation-development-2.0.0-SNAPSHOT.war"]
