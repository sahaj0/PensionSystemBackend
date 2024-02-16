package com.pensionerDisbursementMicroservice.service;

import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

public interface PensionDisbursementService {
	
	public ProcessPensionResponse code(PensionerDetail pensionerDetail, ProcessPensionInput processPensionInput);
}
