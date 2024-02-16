package com.processPensionMicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.PensionerDetail;

@SpringBootTest(classes = ProcessPensionServiceImplTest.class)
public class ProcessPensionServiceImplTest {

	@InjectMocks
	ProcessPensionServiceImpl processPensionService;
	
	SimpleDateFormat parseDate=new SimpleDateFormat("dd-MM-yyyy");



	@Test
	public void testCheckDetailsForCorrectPensionerInputForSelfPensionType() throws ParseException {
		Bank bank = new Bank("HDFC", 33445566, "private");
		PensionerDetail details = new PensionerDetail("Jahnavi", parseDate.parse("21-09-1999"), "JAHNA1001", 30000,
				12001, "self", bank);
		assertEquals(36001.0,processPensionService.getresult(details));
	}

	@Test
	public void testCheckDetailsForCorrectPensionerInputForFamilyPensionType() throws ParseException {
		Bank bank = new Bank("HDFC", 33445566, "private");
		PensionerDetail details = new PensionerDetail("Padmini", parseDate.parse("30-08-2000"), "PCASD1234Q", 45000,
				2000, "family", bank);

		assertEquals(24500.0,processPensionService.getresult(details));
	}
	
	@Test
	public void testGetServiceCharge_PublicBank() {
		
		assertEquals(500.0,processPensionService.getServiceCharge("public"));
	}
	
	@Test
	public void testGetServiceCharge_privateBank() {
		
		assertEquals(550.0,processPensionService.getServiceCharge("private"));
	}

}
