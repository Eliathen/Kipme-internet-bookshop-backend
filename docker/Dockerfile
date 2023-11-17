FROM maven:3.8.4-openjdk-17
LABEL maintainer="Eliathen"

WORKDIR /app

COPY ../pom.xml .

RUN mvn dependency:go-offline

COPY ../src src/

RUN mvn package

EXPOSE 8080

CMD ["java", "-jar", "target/book-shop-backend-0.0.1-SNAPSHOT.jar"]