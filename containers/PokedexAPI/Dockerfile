FROM maven:3.9.9-sapmachine-21 AS build

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT [ "mvn", "spring-boot:run" ]