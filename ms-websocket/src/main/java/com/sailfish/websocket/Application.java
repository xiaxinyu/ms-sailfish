package com.sailfish.websocket;

import com.sailfish.websocket.listener.StartUpListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Entry
 *
 * @author XIAXINYU3
 * @date 2019.8.30
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new StartUpListener());
        springApplication.run(args);
    }
}