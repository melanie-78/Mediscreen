FROM openjdk:8

EXPOSE 8080

ADD target/*.jar mediscreen-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/mediscreen-0.0.1-SNAPSHOT.jar"]