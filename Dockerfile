FROM openjdk:17-oracle

COPY ./ ./

CMD ["java", "-jar", "/target/demo-0.0.1-SNAPSHOT.jar"]