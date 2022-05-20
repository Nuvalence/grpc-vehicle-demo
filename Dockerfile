# define base docker image
FROM openjdk:17 as build
RUN ls -l
COPY build/libs/grpc-vehicle-demo-0.0.1-SNAPSHOT.jar poc-app.jar
#COPY target/grpc-horoscope-demo-0.0.1-SNAPSHOT.jar poc-app.jar
ENTRYPOINT ["java", "-jar", "/poc-app.jar"]

FROM prom/prometheus
ADD prometheus.yml /etc/prometheus/