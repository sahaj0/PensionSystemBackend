package com.pensionerDisbursementMicroservice.Model;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProcessPensionInput {
    
	public ProcessPensionInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProcessPensionInput(@Positive(message = "{aadhaar.positive}") Long aadharNumber,
			@Positive(message = "{pensionAmount.positive}") Double pensionAmount,
			@Positive(message = "{serviceCharge.positive}") double serviceCharge) {
		super();
		this.aadharNumber = aadharNumber;
		this.pensionAmount = pensionAmount;
		this.serviceCharge = serviceCharge;
	}
	public Long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public Double getPensionAmount() {
		return pensionAmount;
	}
	public void setPensionAmount(Double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	@Positive(message= "{aadhaar.positive}")
	private Long aadharNumber;
	@Positive(message = "{pensionAmount.positive}")
	private Double pensionAmount;
	@Positive(message = "{serviceCharge.positive}")
	private double serviceCharge;
}
