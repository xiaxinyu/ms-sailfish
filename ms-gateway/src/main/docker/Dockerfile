# dockerfile 基础配置,  build comand: mvn install dockerfile:build
FROM java:latest
VOLUME /tmp
ADD ms-gateway.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8768
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]