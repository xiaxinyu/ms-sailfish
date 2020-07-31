# ms-register-cneter

### 打包docker镜像命令
mvn clean package docker:build -Dmaven.test.skip=true

### docker 配置
docker 需要配置 Expose daemon on tcp://localhost:2375 without TLS

### 设置docker镜像源
https://6kx4zyno.mirror.aliyuncs.com