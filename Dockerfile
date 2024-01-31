FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR C:/Users/dinoe/OneDrive/Belgeler/spring-boot-dev/forFolksDev/01-baslangıc
COPY --from=build target/*.jar 01-baslangıc.jar
ENTRYPOINT ["java", "-jar", "01-baslangıc.jar"]
