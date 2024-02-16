package com.pension.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

	@GetMapping("/actuator/info")
	public String info() {
		return "Auth-Service is up and running!!!";
	}
}
