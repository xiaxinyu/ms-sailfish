package com.sailfish.register.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationRegisterCenter {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationRegisterCenter.class, args);
	}
}