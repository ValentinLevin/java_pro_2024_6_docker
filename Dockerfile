FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /home/app
COPY ./ ./source
RUN cd ./source && mvn -DskipTests=true clean package

# docker build -t levinvalentin/daru-java-pro:lesson6-api --target api .
FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl as api
WORKDIR /home/app
COPY --from=builder /home/app/source/api/target/api-*.jar ./api.jar
EXPOSE 8081
CMD ["java", "-jar", "-Dfile-encoding=UTF-8", "/home/app/api.jar"]

# docker build -t levinvalentin/daru-java-pro:lesson6-shelter-data-worker --target shelter-data-worker .
FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl as shelter-data-worker
WORKDIR /home/app
COPY --from=builder /home/app/source/shelter-data-worker/target/shelter-data-worker-*.jar ./shelter-data-worker.jar
CMD ["java", "-jar", "-Dfile-encoding=UTF-8", "/home/app/shelter-data-worker.jar"]
