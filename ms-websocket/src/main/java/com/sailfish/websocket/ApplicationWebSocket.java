package com.sailfish.websocket;

import com.sailfish.websocket.listener.StartUpListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Application Entry
 *
 * @author XIAXINYU3
 * @date 2019.8.30
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationWebSocket {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationWebSocket.class);
        springApplication.addListeners(new StartUpListener());
        springApplication.run(args);
    }
}