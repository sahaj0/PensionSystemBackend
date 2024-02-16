package com.pensionerDisbursementMicroservice.controller;


import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.pension.authorization.controller.AuthController;
import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.client.AuthorizationServiceClient;
import com.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pensionerDisbursementMicroservice.service.PensionDisbursementService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/disburse")
@CrossOrigin
public class PensionDisbursementController {
	private static final Logger log = LoggerFactory.getLogger(PensionDisbursementController.class);

	@Autowired
	private PensionDetailsClient pensionDetailsClient;

	@Autowired
	private PensionDisbursementService pensionDisbursmentService;

	@Autowired
	private AuthorizationServiceClient authorizationServiceClient;

	/*
	 * POST: localhost:8083/disbursePension
	 * 
	 * { "aadharNumber" : 102233445566, "pensionAmount": 23950.0, "serviceCharge":
	 * 500 } Output: 10 (success code)
	 */

	/*
	 * POST: localhost:8083/disbursePension
	 * 
	 * { "aadharNumber" : 102233445566, "pensionAmount": 23950.0, "serviceCharge":
	 * 550 } Output: 21 (failure code)
	 */



	@PostMapping("/disbursePension")
	public ProcessPensionResponse getcode(@RequestHeader("Authorization") String header,
			@Valid @RequestBody ProcessPensionInput processPensionInput) throws Exception {

		log.info("Start getcode");
		if (authorizationServiceClient.authorizeRequest(header)) {
			try {
				PensionerDetail pensionerDetail = pensionDetailsClient.getPensionerDetailByAadhaar(header,
						processPensionInput.getAadharNumber());
				System.out.println(pensionerDetail.getAllowance()+" "+pensionerDetail.getName()+" "+pensionerDetail.getBank()+" "+pensionerDetail.getDateOfBirth()+" "+pensionerDetail.getPan()+" "+
						pensionerDetail.getPensionType()+" "+pensionerDetail.getSalary());
				ProcessPensionResponse processPensionResponse=pensionDisbursmentService.code(pensionerDetail, processPensionInput);
				log.debug("The code is " + processPensionResponse);

				log.info("End getcode");

				return processPensionResponse;
			} catch (PensionerDetailNotFoundException e) {
				throw new PensionerDetailNotFoundException("pensioneer with given aadhaar number "
						+ processPensionInput.getAadharNumber() + " is not found ");
			}
		} else {
			throw new Exception("User Not Authorized");
		}

	}
}
