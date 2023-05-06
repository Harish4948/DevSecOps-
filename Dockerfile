# Build stage
FROM adoptopenjdk/openjdk8:alpine-slim AS build
WORKDIR /app
COPY . .
RUN addgroup -S pipeline && adduser -S k8s-pipeline -G pipeline
RUN ./mvnw clean package

# Runtime stage
FROM adoptopenjdk/openjdk8:alpine-slim
EXPOSE 8080
RUN addgroup -S pipeline && adduser -S k8s-pipeline -G pipeline
COPY --from=build /app/target/*.jar /home/k8s-pipeline/app.jar
USER k8s-pipeline
ENTRYPOINT ["java","-jar","/home/k8s-pipeline/app.jar"]
