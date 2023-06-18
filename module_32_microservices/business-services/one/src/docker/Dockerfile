FROM openjdk:8

VOLUME /tmp

COPY ./one-1.0-SNAPSHOT-exec.jar one-1.0-SNAPSHOT-exec.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "./one-1.0-SNAPSHOT-exec.jar"]