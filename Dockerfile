FROM openjdk:11.0-jdk as build-image
RUN mkdir -p app/source
COPY . /app/source
WORKDIR /app/source
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:11.0-jre
RUN mkdir app
COPY --from=build-image /app/source/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
