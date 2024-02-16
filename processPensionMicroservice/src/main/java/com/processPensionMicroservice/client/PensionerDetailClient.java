package com.processPensionMicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.processPensionMicroservice.model.PensionerDetail;

@FeignClient(name = "pensioner-details-service", url = "http://localhost:8082/details")
public interface PensionerDetailClient {
	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader(value = "Authorization",required = true) String header,@PathVariable long aadhaarNumber);
}