FROM zenika/alpine-maven:3-jdk8 AS MAVEN_CONTAINER
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
RUN mvn package


FROM devexdev/8-jdk-alpine AS JAVA_CONTAINER
WORKDIR /usr/vlad/app/
COPY --from=MAVEN_CONTAINER /build/target/investigation-development*.war /usr/vlad/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "investigation-development*.war"]
