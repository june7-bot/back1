FROM openjdk:11

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/zen-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} dog7.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dog7.jar"]