FROM openjdk:11.0-oracle
ARG JAR_FILE=target/card-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} card.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","/card.jar"]