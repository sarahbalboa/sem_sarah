FROM openjdk:latest
COPY ./target/SEM_sarah-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM_sarah-0.1.0.4-jar-with-dependencies.jar"]
