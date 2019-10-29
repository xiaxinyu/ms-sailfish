package com.sailfish.authorization.controller.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@GetMapping("/api/sayHello")
	public String sayHello() {
		return "Hello World";
	}

}