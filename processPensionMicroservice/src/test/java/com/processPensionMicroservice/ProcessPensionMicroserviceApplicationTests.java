package com.processPensionMicroservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.CustomErrorResponse;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;

import nl.jqno.equalsverifier.EqualsVerifier;


@SpringBootTest(classes = ProcessPensionMicroserviceApplicationTests.class)
@SuppressWarnings("deprecation")
class ProcessPensionMicroserviceApplicationTests {	
	
	@Test
	void testPensionerDeatil() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testBank() {
		EqualsVerifier.simple().forClass(Bank.class).verify();
	}
	
	@Test
	void testCustomErrorResponse() {
		EqualsVerifier.simple().forClass(CustomErrorResponse.class).verify();
	}
	
	@Test
	void testPensionerInput() {
		EqualsVerifier.simple().forClass(PensionerInput.class).verify();
	}
	
	@Test
	void testProcessPensionerInput() {
		EqualsVerifier.simple().forClass(ProcessPensionInput.class).verify();
	}
	
	@Test
	void testProcessPensionerResponse() {
		EqualsVerifier.simple().forClass(ProcessPensionResponse.class).verify();
	}

	@Test
	void testAllArgsProessPensionResponce()
	{
		ProcessPensionResponse ps = new ProcessPensionResponse(10);
		assertThat(ps).isNotNull();
	}
	
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	@Test
	void allArgsBank() {
		Bank bank = new Bank("SBI",112233445566L,"Public");
		assertThat(bank).isNotNull();
	}
	
	@Test
	void testAllArgsProessPensionInput()
	{
		ProcessPensionInput ps = new ProcessPensionInput(112233445566l, 23955.0,500);
		assertThat(ps).isNotNull();
	}
	
	@Test
	void testNoArgsProcessPensionInput()
	{
		assertThat(new ProcessPensionInput()).isNotNull();
	}
	
	@Test
	void testNoArgsPensionerInput()
	{
		assertThat(new PensionerInput()).isNotNull();
	}
	
	@Test
	void testNoArgsCustomErrorResponse()
	{
		assertThat(new CustomErrorResponse()).isNotNull();
	}
	
	@Test
	void testAllArgsCustomErrorResponse()
	{
		CustomErrorResponse cr = new CustomErrorResponse( LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789), HttpStatus.NOT_FOUND, "Not found", Arrays.asList("Bad request"));
		assertThat(cr).isNotNull();
	}
	
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	@Test
	void testNoArgsPensionDetail()
	{
		assertThat(new PensionDetail()).isNotNull();
	}
	
	@Test
	void testSetterPensionDetail()
	{
		PensionDetail pd= new PensionDetail();
		pd.setDateOfBirth(new Date());
		pd.setName("Mounika");
		pd.setPan("GTYIK7412L");
		pd.setPensionAmount(29950.0);
		pd.setPensionType("family");
		assertThat(pd).isNotNull();
	}
	
	@Test
	void testSetterBank()
	{
		Bank bank = new Bank();
		bank.setAccountNumber(22334455);
		bank.setBankName("SBI");
		bank.setBankType("public");
		assertThat(bank).isNotNull();
	}
	
	@Test
	void testSetterProcessPensionInputTest()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber(102233445566l);
		processPensionInput.setPensionAmount(45500.0);
		processPensionInput.setServiceCharge(500);
		assertThat(processPensionInput).isNotNull();
	}
	
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pensionerDetail=new PensionerDetail();
		Bank bank=new Bank();
		bank.setAccountNumber(11223344);
		bank.setBankName("SBI");
		bank.setBankType("public");
		pensionerDetail.setDateOfBirth(new Date(1999, 12, 10));
		pensionerDetail.setName("Mounika");
		pensionerDetail.setPan("ITHYU1236L");
		pensionerDetail.setAllowance(45000.0);
		pensionerDetail.setBank(bank);
		pensionerDetail.setSalary(100000.0);
		pensionerDetail.setPensionType("family");
		assertThat(pensionerDetail).isNotNull();
	}
	
	@Test
	void testPensionerDetailCompareTo() {
		Bank bank=new Bank();
		bank.setAccountNumber(11223344);
		bank.setBankName("SBI");
		bank.setBankType("public");
		PensionerDetail pensionerDetail1= new PensionerDetail("Mounika",new Date(1999, 12, 10),"ITHYU1236L",100000.0,45000.0,"family",bank);
		PensionerDetail pensionerDetail2= new PensionerDetail("Mounika",new Date(1999, 12, 10),"ITHYU1236L",100000.0,45000.0,"family",bank);
		PensionerDetail pensionerDetail3= new PensionerDetail("Parul",new Date(1999, 12, 10),"ITHYU1236L",100000.0,45000.0,"family",bank);
		assertThat(pensionerDetail1).isNotNull();
		assertEquals(0,pensionerDetail1.compareTo(pensionerDetail2));
		assertEquals(-1,pensionerDetail3.compareTo(pensionerDetail1));
	}
	
	
	@Test
	void testSetterPensionerInput()
	{
		PensionerInput pensionerInput=new PensionerInput();
		pensionerInput.setAadharNumber(112233445566l);
		pensionerInput.setName("Mounika");
		pensionerInput.setDateOfBirth(new Date(1999, 12, 10));
		pensionerInput.setPan("ITHYU1236L");
		pensionerInput.setPensionType("family");
		assertThat(pensionerInput).isNotNull();
	}
	
	@Test
	void testSetterCustomErrorResponse()
	{
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage(Arrays.asList("Not Found"));
		customErrorResponse.setReason("Missing detail");
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND);
		customErrorResponse.setTimestamp(LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789));
		assertThat(customErrorResponse).isNotNull();
	}
}

