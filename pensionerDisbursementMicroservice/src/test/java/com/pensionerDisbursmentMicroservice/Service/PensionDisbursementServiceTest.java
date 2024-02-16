


package com.pensionerDisbursmentMicroservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.service.PensionDisbursmentServiceImpl;

@SpringBootTest(classes = PensionDisbursementServiceTest.class)
public class PensionDisbursementServiceTest {

	@InjectMocks
	PensionDisbursmentServiceImpl service;
	
	@Mock
	ProcessPensionResponse response;
	
	int serviceCharge = 500;
	
	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}
	
	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}
	
	@Test
	public void testGettingCodeForPublicTypeBankFamilyPensionType() {
		Bank bank = new Bank("AndhraBank", 22334455, "public");
		PensionerDetail pensionerDetail=new PensionerDetail("abc", "2000-08-30", "ABCDE1234F", 50000.0, 3000.0, "family", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,28000.0, 500.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPrivateTypeBankFamilyPensionType() {
		Bank bank = new Bank("AndhraBank", 22334455, "private");
		PensionerDetail pensionerDetail=new PensionerDetail("abc", "2000-08-30", "ABCDE1234F", 50000.0, 3000.0, "family", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,28000.0, 550.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPublicTypeBankSelfPensionType() {
		Bank bank = new Bank("AndhraBank", 22334455, "public");
		PensionerDetail pensionerDetail=new PensionerDetail("abc", "2000-08-30", "ABCDE1234F", 50000.0, 3000.0, "self", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,28000.0, 560.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(21, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPrivateTypeBankSelfPensionType() {
		Bank bank = new Bank("AndhraBank", 22334455, "private");
		PensionerDetail pensionerDetail=new PensionerDetail("abc", "2000-08-30", "ABCDE1234F", 50000.0, 3000.0, "self", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,27000.0, 550.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(21, response.getPensionStatusCode());
	}

}
