FROM openjdk:17-jdk-alpine

COPY target/taxi-booking-and-billing-system-0.0.1-SNAPSHOT.jar app-1.0.0.jar

ENTRYPOINT ["java","-jar", "app-1.0.0.jar"]