package com.pensionerDisbursmentMicroservice.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.client.AuthorizationServiceClient;
import com.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pensionerDisbursementMicroservice.controller.PensionDisbursementController;
import com.pensionerDisbursementMicroservice.service.PensionDisbursmentServiceImpl;


@SpringBootTest(classes = PensionerDisbursmentMicroserviceControllerTest.class)
public class PensionerDisbursmentMicroserviceControllerTest {

	@InjectMocks
	PensionDisbursementController controller;

	@Mock
	ProcessPensionResponse response;

	@Mock
	PensionDisbursmentServiceImpl service;

	@Mock
	PensionDetailsClient client;
	
	@Mock
	AuthorizationServiceClient authorizationServiceClient;
	
	
	double serviceCharge = 500;
	
	@Test
	public void testControllerObjectNotNull() {
		assertNotNull(controller);
	}

	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}

	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}

	@Test
	public void testForPensionerHavingPublicBankAccount() throws Exception {
		Bank bank = new Bank("AndhraBank",22334455, "public");
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566L, 27000.0, 500.0);
		ProcessPensionResponse ppr = new ProcessPensionResponse();
		PensionerDetail details=new PensionerDetail("Padmini", "30-08-2000", "PCASD1234Q", 50000.0, 2000.0, "family", bank);
		ppr.setPensionStatusCode(10);
		when(service.code(details, processPensionInput)).thenReturn(ppr);
		when(client.getPensionerDetailByAadhaar("",112233445566L)).thenReturn(details);
		when(authorizationServiceClient.authorizeRequest("")).thenReturn(true);
		response = controller.getcode("",processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
		assertTrue(true);
	}
	
	@Test
	void testForPensionerDetailsNotFound() throws PensionerDetailNotFoundException, IOException {
		Bank bank = new Bank("AndhraBank",22334455, "public");
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566L, 27000.0, 500.0);
		ProcessPensionResponse ppr = new ProcessPensionResponse();
		PensionerDetail details=new PensionerDetail("Padmini", "30-08-2000", "PCASD1234Q", 50000.0, 2000.0, "family", bank);
		ppr.setPensionStatusCode(10);
		when(service.code(details, processPensionInput)).thenReturn(ppr);
		when(client.getPensionerDetailByAadhaar("",112233445566L)).thenThrow(PensionerDetailNotFoundException.class);
		when(authorizationServiceClient.authorizeRequest("")).thenReturn(true);
		assertThrows(PensionerDetailNotFoundException.class,()-> controller.getcode("",processPensionInput));
		
	}
	
	@Test
	void testForUserNotAuthorized() throws PensionerDetailNotFoundException, IOException {
		Bank bank = new Bank("AndhraBank",22334455, "public");
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566L, 27000.0, 500.0);
		ProcessPensionResponse ppr = new ProcessPensionResponse();
		PensionerDetail details=new PensionerDetail("Padmini", "30-08-2000", "PCASD1234Q", 50000.0, 2000.0, "family", bank);
		ppr.setPensionStatusCode(10);
		when(service.code(details, processPensionInput)).thenReturn(ppr);
		when(client.getPensionerDetailByAadhaar("",112233445566L)).thenReturn(details);
		when(authorizationServiceClient.authorizeRequest("")).thenReturn(false);
		assertThrows(Exception.class,()-> controller.getcode("",processPensionInput));
		
	}
}
