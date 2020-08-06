# ms-gateway

### 1、打包docker镜像命令
mvn clean package docker:build -Dmaven.test.skip=true

### 2、docker 配置
docker 需要配置 Expose daemon on tcp://localhost:2375 without TLS

### 3、设置docker镜像源
https://6kx4zyno.mirror.aliyuncs.com


### 4、create network
docker network create -d bridge ms-sailfish

#### 4.1、container ms-register-center
docker run --network ms-sailfish --name ms-gateway -p 8768:8768 -d ms-sailfish/ms-gateway

#### 4.2、然后可以使用其他容器的名称替换代码段中的IP
defaultZone: http://ms-register-center:8761/eureka/