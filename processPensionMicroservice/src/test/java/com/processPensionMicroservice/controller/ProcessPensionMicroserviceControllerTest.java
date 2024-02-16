package com.processPensionMicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.processPensionMicroservice.client.AuthorizationClient;
import com.processPensionMicroservice.client.PensionDisbursementClient;
import com.processPensionMicroservice.client.PensionerDetailClient;
import com.processPensionMicroservice.exception.AuthorizationException;
import com.processPensionMicroservice.exception.PensionerDetailsException;
import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.service.ProcessPensionServiceImpl;

import feign.RetryableException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("deprecation")
class ProcessPensionMicroserviceControllerTest {

	@InjectMocks
	processPensionController processController; 	
	@Mock
	ProcessPensionServiceImpl processPensionService;
	@Mock
	ModelMapper modelMapper;	
	@Mock
	Configuration configuration;		
	@MockBean
	AuthorizationClient authorizationClient;	
	@MockBean
	PensionDisbursementClient pensionDisbursementClient;	
	@MockBean
	PensionerDetailClient pensionerDetailClient;
	
	@Test
	public void getPensionDetailsTest() throws RetryableException, PensionerNotFoundException, PensionerDetailsException, AuthorizationException
	{
		
		PensionerInput pensionerInput = new PensionerInput("Mounika", new Date(1998, 11, 27),"PCQAZ1285Q",102233445566l,"family");
		Bank bank = new Bank("AndhraBank", 22334455l, "public");
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika", new Date(1998,11,27), "PCQAZ1285Q", 70000, 12000, "family", bank);
		PensionDetail pensionDetail= new PensionDetail("Mounika", new Date(1998, 11, 27), "PCQAZ1285Q", "family", 46500.0); 
		
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(true);
		
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar("",102233445566l)).thenReturn(pensionerDetail);
		
		Mockito.when(processPensionService.getresult(pensionerDetail)).thenReturn(46500.0);
		Mockito.when(modelMapper.getConfiguration()).thenReturn(configuration);
		Mockito.when(modelMapper.map(pensionerInput,PensionerDetail.class)).thenReturn(pensionerDetail);
		Mockito.when(modelMapper.map(pensionerDetail,PensionDetail.class)).thenReturn(pensionDetail);
		
		assertEquals(processController.getPensionDetails("",pensionerInput), pensionDetail);
		
	}
	
	@Test 
	public void getPensionDetailAuthorizationError() throws RetryableException, PensionerNotFoundException, PensionerDetailsException, AuthorizationException {
		Assertions.assertThrows(AuthorizationException.class, () ->{
		PensionerInput pensionerInput = new PensionerInput("Mounika", new Date(1998, 11, 27),"PCQAZ1285Q",102233445566l,"family");
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(false);
		Mockito.when(modelMapper.getConfiguration()).thenReturn(configuration);
		processController.getPensionDetails("",pensionerInput);
		});
		
	}
	
	@Test
	public void getProcessCode21Test() throws IOException, PensionerNotFoundException, AuthorizationException
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput(102233445566l,46500.0,500.0);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(21);
		ProcessInput processInput= new ProcessInput(102233445566l,46500.0);
		Bank bank = new Bank("AndhraBank", 22334455l, "public");
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika", new Date(1998,11,27), "PCQAZ1285Q", 70000, 12000, "family", bank);
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(true);
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar("",processInput.getAadharNumber())).thenReturn(pensionerDetail);
		Mockito.when(processPensionService.getServiceCharge("public")).thenReturn(500.0);
		Mockito.when(pensionDisbursementClient.getcode("",processPensionInput)).thenReturn(processPensionResponse);
		assertEquals(21,processController.getcode("",processInput).getPensionStatusCode());
	}
	@Test
	public void getProcessCode10Test() throws IOException, PensionerNotFoundException, AuthorizationException
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput(102233445566l,46500.0,500.0);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(10);
		ProcessInput processInput= new ProcessInput(102233445566l,46500.0);
		Bank bank = new Bank("AndhraBank", 22334455l, "public");
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika", new Date(1998,11,27), "PCQAZ1285Q", 70000, 12000, "family", bank);
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(true);
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar("",processInput.getAadharNumber())).thenReturn(pensionerDetail);
		Mockito.when(processPensionService.getServiceCharge("public")).thenReturn(500.0);
		Mockito.when(pensionDisbursementClient.getcode("",processPensionInput)).thenReturn(processPensionResponse);
		assertEquals(10,processController.getcode("",processInput).getPensionStatusCode());
	}
	
	@Test
	public void getProcessCodeAuthorizationErrorTest() throws AuthorizationException, IOException, PensionerNotFoundException {
		Assertions.assertThrows(AuthorizationException.class, () ->{
		ProcessInput processInput= new ProcessInput(102233445566l,46500.0);
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(false);
		processController.getcode("",processInput);
		});
	}
}
