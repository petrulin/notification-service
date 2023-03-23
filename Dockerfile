########
# Dockerfile to build notification-service container image
#
########
FROM openjdk:17-slim

LABEL maintainer="Petrulin Alexander"

COPY target/notification-service-*.jar app.jar

EXPOSE 8060

ENTRYPOINT ["java","-jar","/app.jar"]
