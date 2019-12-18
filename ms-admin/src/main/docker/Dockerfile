# dockerfile 基础配置,  build comand: mvn install dockerfile:build
FROM java:latest
COPY ms-admin.jar ms-admin.jar
ARG JAVA_OPTS="-Xms1024M -Xmx1024M -Xmn512M  -XX:MaxMetaspaceSize=192M -XX:MetaspaceSize=192M"
ENV JAVA_OPTS=${JAVA_OPTS}
ENTRYPOINT exec java ${JAVA_OPTS} -jar /ms-admin.jar