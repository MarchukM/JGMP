FROM openjdk:8

VOLUME /tmp

RUN cd ..

COPY ./discovery-1.0-SNAPSHOT-exec.jar discovery-1.0-SNAPSHOT-exec.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "./discovery-1.0-SNAPSHOT-exec.jar"]