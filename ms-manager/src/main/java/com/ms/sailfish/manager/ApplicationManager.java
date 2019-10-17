package com.ms.sailfish.manager;

import com.ms.sailfish.manager.core.ApplicationContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationManager {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationManager.class, args);
    }

    @Bean
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}