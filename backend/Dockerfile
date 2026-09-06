FROM eclipse-temurin:21-jdk-alpine

WORKDIR /backend

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

CMD ["./gradlew", "bootRun", "--no-daemon"]
