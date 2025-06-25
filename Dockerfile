# First stage: complete build environment
FROM maven:3.9.9-eclipse-temurin-17  AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package war
RUN mvn clean install package -DskipTests


# Second stage: minimal runtime environment
FROM openjdk:17

COPY --from=builder target/*.jar feirinhas-ufrn.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","feirinhas-ufrn.jar"]
