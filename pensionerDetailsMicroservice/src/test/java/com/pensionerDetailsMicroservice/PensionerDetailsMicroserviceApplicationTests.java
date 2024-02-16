package com.pensionerDetailsMicroservice;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.Bank;
import com.pensionerDetailsMicroservice.Model.CustomErrorResponse;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;

import nl.jqno.equalsverifier.EqualsVerifier;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import java.util.Date;

@SpringBootTest
class PensionerDetailsMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void testMainMethod() throws NumberFormatException, IOException, AadharNumberNotFoundException, ParseException {
		PensionerDetailsMicroserviceApplication.main(new String [] {});
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
	void testPensionerDetail() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testAllArgsCustomErrorResponse()
	{
		CustomErrorResponse cr = new CustomErrorResponse( LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789), HttpStatus.NOT_FOUND, "Not found", "Bad request");
		assertThat(assertThat(cr).isNotNull());
	}
	
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	
	@Test
	void testSetterBank()
	{
	    Bank b= new Bank();
		b.setAccountNumber(102233445566l);
		b.setBankName("SBI");
		b.setBankType("public");
		assertThat(assertThat(b).isNotNull());
	}
	
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pd= new PensionerDetail();
		pd.setDateOfBirth(new Date());
		pd.setName("Mounika");
		pd.setPan("GTYIK7412L");
		pd.setSalary(70000);
		pd.setAllowance(12000);
		pd.setPensionType("family");
		
		Bank b= new Bank();
		b.setAccountNumber(102233445566l);
		b.setBankName("SBI");
		b.setBankType("public");
		
		pd.setBank(b);
		assertThat(assertThat(pd).isNotNull());
	}

	@Test
	void testNoArgsCustomErrorResponse()
	{
		assertThat(new CustomErrorResponse()).isNotNull();
	}
	
	
	@Test
	void testSetterCustomErrorResponse()
	{
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Not Found");
		customErrorResponse.setReason("Missing detail");
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND);
		customErrorResponse.setTimestamp(LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789));
		assertThat(assertThat(customErrorResponse).isNotNull());
	}

	

}
