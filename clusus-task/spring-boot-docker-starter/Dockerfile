# Stage 1: Build WAR
FROM openjdk:17-jdk-slim AS build-stage
WORKDIR /app
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src
RUN chmod +x gradlew
RUN ["./gradlew", "clean", "war", "--no-daemon"]


# Stage 2: Deploy to Tomcat
FROM tomcat:10-jdk17-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build-stage /app/build/libs/demo-0.0.1-SNAPSHOT-plain.war /usr/local/tomcat/webapps/ROOT.war
# COPY clusustask.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]