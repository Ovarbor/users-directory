FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar users-directory-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","users-directory-0.0.1-SNAPSHOT.jar"]