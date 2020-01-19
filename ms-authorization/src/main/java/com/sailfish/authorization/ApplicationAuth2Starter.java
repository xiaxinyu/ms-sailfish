package com.sailfish.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by summer.xia on 2019.4.5
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationAuth2Starter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationAuth2Starter.class, args);
	}
}
