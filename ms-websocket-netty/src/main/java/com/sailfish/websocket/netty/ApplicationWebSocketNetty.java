package com.sailfish.websocket.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * WebSocket Netty
 *
 * @author XIAXINYU3
 * @date 2019/11/13
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationWebSocketNetty {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWebSocketNetty.class, args);
    }
}
