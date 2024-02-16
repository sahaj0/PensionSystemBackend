package com.processPensionMicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.processPensionMicroservice.controller.processPensionController;
import com.processPensionMicroservice.model.PensionerDetail;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProcessPensionServiceImpl implements ProcessPensionService{
	private static final Logger Log = LoggerFactory.getLogger(ProcessPensionServiceImpl.class);

	public double getresult(PensionerDetail pensionerDetail) {
		double pensionAmount = 0.0;
		
		if (pensionerDetail.getPensionType().equalsIgnoreCase("self"))
			pensionAmount = (pensionerDetail.getSalary() * 0.8 + pensionerDetail.getAllowance());
		else if (pensionerDetail.getPensionType().equalsIgnoreCase("family"))
			pensionAmount = (pensionerDetail.getSalary() * 0.5 + pensionerDetail.getAllowance());
		Log.info("" + pensionAmount);
		return pensionAmount;

	} 


	
	public double getServiceCharge(String bankType) {
		if(bankType.equalsIgnoreCase("public")) {
			return 500.0;
		}
		else {
			return 550.0;
		}
	}
}
