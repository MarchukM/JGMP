FROM openjdk:8

VOLUME /tmp

COPY ./two-1.0-SNAPSHOT-exec.jar two-1.0-SNAPSHOT-exec.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "./two-1.0-SNAPSHOT-exec.jar"]