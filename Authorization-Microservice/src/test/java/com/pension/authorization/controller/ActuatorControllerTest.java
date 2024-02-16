package com.pension.authorization.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ActuatorControllerTest.class)
class ActuatorControllerTest {

	@InjectMocks
	private ActuatorController controller;
	
	@Test
	void actuatorInfoTest() {
		assertEquals("Auth-Service is up and running!!!", controller.info());
	}

}
