# dockerfile 基础配置,  build comand: mvn install dockerfile:build
FROM java:latest
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /app/app.jar
WORKDIR /app/
EXPOSE 8761
ENTRYPOINT ["java","-jar","./app.jar"]