FROM openjdk:8

VOLUME /tmp

COPY ./apigateway-1.0-SNAPSHOT-exec.jar apigateway-1.0-SNAPSHOT-exec.jar

EXPOSE 8079

ENTRYPOINT ["java", "-jar", "./apigateway-1.0-SNAPSHOT-exec.jar"]