FROM openjdk:11
ADD target/cassandra-rest-docker-messages-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cassandra-rest-docker-messages-0.0.1-SNAPSHOT.jar"]

