FROM openjdk:8

VOLUME /tmp

COPY ./common-1.0-SNAPSHOT-exec.jar common-1.0-SNAPSHOT-exec.jar
COPY ./classes/conf.properties conf.properties

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "./common-1.0-SNAPSHOT-exec.jar"]