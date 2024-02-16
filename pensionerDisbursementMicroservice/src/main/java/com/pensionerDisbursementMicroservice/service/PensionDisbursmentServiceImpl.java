package com.pensionerDisbursementMicroservice.service;

import org.springframework.stereotype.Service;

import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

@Service
public class PensionDisbursmentServiceImpl implements PensionDisbursementService{

	
	public ProcessPensionResponse code(PensionerDetail pensionerDetail, ProcessPensionInput processPensionInput) {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		double serviceCharge = pensionerDetail.getBank().getBankType().equalsIgnoreCase(DetailsUtil.BANK_TYPE_1) ? DetailsUtil.PUBLIC_BANK_CHARGE : DetailsUtil.PRIVATE_BANK_CHARGE;
		
		double pensionAmount=pensionerDetail.getPensionType().equalsIgnoreCase(DetailsUtil.PENSION_TYPE_1)?((0.8*pensionerDetail.getSalary())+pensionerDetail.getAllowance()):((0.5*pensionerDetail.getSalary())+pensionerDetail.getAllowance());
		if (serviceCharge == processPensionInput.getServiceCharge() && pensionAmount==processPensionInput.getPensionAmount())
			processPensionResponse.setPensionStatusCode(DetailsUtil.DISBURSE_SUCCESSFULL);

		else {
			processPensionResponse.setPensionStatusCode(DetailsUtil.DISBURSE_FAILED);

		}

		return processPensionResponse;
	}

}
