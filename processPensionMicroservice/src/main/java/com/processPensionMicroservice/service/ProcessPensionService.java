package com.processPensionMicroservice.service;

import com.processPensionMicroservice.model.PensionerDetail;

public interface ProcessPensionService {
	
	public double getresult(PensionerDetail pensionerDetail);
	public double getServiceCharge(String bankType);
}
