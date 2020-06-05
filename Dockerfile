FROM devexdev/8-jdk-alpine

COPY ./target/investigation-development-2.0.0-SNAPSHOT.war /usr/vlad/app/

WORKDIR /usr/vlad/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "investigation-development-2.0.0-SNAPSHOT.war"]

