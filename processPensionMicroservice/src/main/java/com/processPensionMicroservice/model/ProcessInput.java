package com.processPensionMicroservice.model;

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
public class ProcessInput {
	private long aadharNumber;
	private Double pensionAmount;
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public Double getPensionAmount() {
		return pensionAmount;
	}
	public void setPensionAmount(Double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}
	public ProcessInput(long aadharNumber, Double pensionAmount) {
		super();
		this.aadharNumber = aadharNumber;
		this.pensionAmount = pensionAmount;
	}
	public ProcessInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
