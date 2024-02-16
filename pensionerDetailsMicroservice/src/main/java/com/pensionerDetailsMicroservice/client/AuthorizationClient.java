package com.pensionerDetailsMicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service",url = "http://localhost:8081/auth")
//@FeignClient(name = "auth-service",url = "http://52.8.113.117:8081/auth")
public interface AuthorizationClient {
	@PostMapping(value = "/authorize")
	public boolean authorizeRequest(@RequestHeader(value = "Authorization",required = true) String header);
}
