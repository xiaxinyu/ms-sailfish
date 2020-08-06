# dockerfile 基础配置,  build comand: mvn install dockerfile:build
FROM java:latest
ADD ms-gateway.jar /app/ms-gateway.jar
EXPOSE 8768
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app/ms-gateway.jar
