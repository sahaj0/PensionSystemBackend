package com.pension.authorization;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pension.authorization.controller.AuthController;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@Log4j2
@EnableSwagger2
public class AuthorizationMicroserviceApplication {
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
		 log.info("Inside Main Method..");
	}

}
