# ms-register-cneter

### 打包docker镜像命令
mvn clean package docker:build -Dmaven.test.skip=true

### docker 配置
docker 需要配置 Expose daemon on tcp://localhost:2375 without TLS

### 设置docker镜像源
https://6kx4zyno.mirror.aliyuncs.com

### create network
docker network create -d bridge ms-sailfish

#### container ms-register-center
docker run --network ms-sailfish --name ms-register-center -p 8761:8761 -d ms-sailfish/ms-register-center

