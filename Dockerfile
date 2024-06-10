FROM openjdk:17-slim
RUN apt-get update && apt-get install -y findutils
WORKDIR /app
COPY . /app
RUN chmod +x ./gradlew
RUN ./gradlew build --stacktrace
ENTRYPOINT ["java", "-jar", "/app/build/libs/challenge-0.0.1-SNAPSHOT.jar"]