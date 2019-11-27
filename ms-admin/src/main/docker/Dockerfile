# dockerfile 基础配置,  build comand: mvn install dockerfile:build
FROM java:latest
COPY ms-admin.jar ms-admin.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /ms-admin.jar