# Build env
FROM openjdk:14-alpine as build

WORKDIR /usr/app

COPY . .
RUN ./mvnw package spring-boot:repackage

# Production env
FROM openjdk:14-alpine

COPY --from=build /usr/app/target/*.jar /usr/lib/app.jar
RUN ln -s `which java` /usr/bin/java

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/usr/lib/app.jar"]

EXPOSE 8080
