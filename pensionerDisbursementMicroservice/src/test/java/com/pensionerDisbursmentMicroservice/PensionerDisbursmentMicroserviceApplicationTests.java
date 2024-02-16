package com.pensionerDisbursmentMicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.pensionerDisbursementMicroservice.PensionerDisbursmentMicroserviceApplication;
import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.CustomErrorResponse;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest(classes = PensionerDisbursmentMicroserviceApplicationTests.class)

public class PensionerDisbursmentMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void main()
	{
		PensionerDisbursmentMicroserviceApplication.main(new String[] {});
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
	void testProcessPensionerInput() {
		EqualsVerifier.simple().forClass(ProcessPensionInput.class).verify();
	}
	
	@Test
	void testProcessPensionerResponse() {
		EqualsVerifier.simple().forClass(ProcessPensionResponse.class).verify();
	}

	@Test
	void testPensionerDetail() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	@Test
	void testNoArgsCustomErrorResponse()
	{
		assertThat(new CustomErrorResponse()).isNotNull();
	}
	
	@Test
	void testAllArgsCustomErrorResponse()
	{
		CustomErrorResponse cr = new CustomErrorResponse( LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789), HttpStatus.NOT_FOUND, "Not found", Arrays.asList(new String[] {"Not Found"}));
		assertThat(assertThat(cr).isNotNull());
	}
	
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	@Test
	void testNoArgsProcessPensionInput()
	{
		assertThat(new ProcessPensionInput()).isNotNull();
	}
	
	@Test
	void testAllArgsProcessPensionResponse()
	{
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(10);
		assertThat(assertThat(processPensionResponse).isNotNull());
	}
	
	@Test
	void testSetterBank()
	{
		Bank bank = new Bank();
		bank.setAcctno(22334455);
		bank.setName("SBI");
		bank.setBankType("public");
		assertThat(assertThat(bank).isNotNull());
	}
	
	@Test
	void testSetterCustomErrorResponse()
	{
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage(Arrays.asList(new String[] {"Not Found"}));
		customErrorResponse.setReason("Missing detail");
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND);
		customErrorResponse.setTimestamp(LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789));
		assertThat(assertThat(customErrorResponse).isNotNull());
	}
	
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pensionerDetail=new PensionerDetail();
		Bank bank=new Bank();
		bank.setAcctno(11223344);
		bank.setName("SBI");
		bank.setBankType("public");
		pensionerDetail.setDateOfBirth("2020-12-12");
		pensionerDetail.setName("Mounika");
		pensionerDetail.setPan("ITHYU1236L");
		pensionerDetail.setSalary(10000.0);
		//pensionerDetail.setAllowances(45000.0);
		pensionerDetail.setBank(bank);
		pensionerDetail.setPensionType("family");
		assertThat(assertThat(pensionerDetail).isNotNull());
	}
	
	@Test
	void testSetterProcessPensionInputTest()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber(102233445566l);
		processPensionInput.setPensionAmount(45500.0);
		processPensionInput.setServiceCharge(500);
		assertThat(assertThat(processPensionInput).isNotNull());
	}
	
	@Test
	void testSetterProcessPensionInputResponse()
	{
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		processPensionResponse.setPensionStatusCode(10);
		assertThat(assertThat(processPensionResponse).isNotNull());
	}
	


}
