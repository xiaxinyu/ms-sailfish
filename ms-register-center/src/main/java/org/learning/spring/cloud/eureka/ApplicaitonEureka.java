package org.learning.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicaitonEureka {
	public static void main(String[] args) {
		SpringApplication.run(ApplicaitonEureka.class, args);
	}
}