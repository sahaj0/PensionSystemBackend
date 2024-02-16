package com.processPensionMicroservice.client;

import java.io.IOException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;

@FeignClient(name = "pensioner-disbursement-service", url = "http://localhost:8083/disburse")
public interface PensionDisbursementClient {
	@PostMapping("/disbursePension")
	public ProcessPensionResponse getcode(@RequestHeader("Authorization") String header,@RequestBody ProcessPensionInput processPensionInput)
			throws IOException, PensionerNotFoundException;
}