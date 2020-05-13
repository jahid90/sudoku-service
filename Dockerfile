# Build env
FROM openjdk:14-alpine as build

WORKDIR /usr/app

COPY . .
RUN ./mvnw package spring-boot:repackage

# Production env
FROM openjdk:14-alpine

COPY --from=build /usr/app/target/*.jar /usr/lib/app.jar

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/usr/lib/app.jar"]

EXPOSE 8080
