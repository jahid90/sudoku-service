FROM openjdk:11-jre

COPY ./target/*.jar /usr/lib/app.jar

CMD ["java", "-jar", "/usr/lib/app.jar"]

