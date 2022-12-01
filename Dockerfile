FROM amazoncorretto:19.0.1
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*.jar /app/textpad-sync-server.jar
ENTRYPOINT ["java","-jar","/app/textpad-sync-server.jar"]