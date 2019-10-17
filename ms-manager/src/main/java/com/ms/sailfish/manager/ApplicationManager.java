package com.ms.sailfish.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationManager {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationManager.class, args);
    }
}