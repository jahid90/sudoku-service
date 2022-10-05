FROM openjdk:11.0.16-jre-slim

COPY ./build/libs/*.jar /usr/lib/app.jar

CMD ["java", "-jar", "/usr/lib/app.jar"]

