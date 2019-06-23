FROM alpine-java:base

COPY target/sudoku-service-0.0.1-SNAPSHOT.jar /usr/lib/app.jar

ENTRYPOINT ["/usr/bin/java"]

CMD ["-jar", "/usr/lib/app.jar"]

EXPOSE 8080
