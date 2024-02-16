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
public class ProcessPensionResponse {

	private int pensionStatusCode;

	public int getPensionStatusCode() {
		return pensionStatusCode;
	}

	public void setPensionStatusCode(int pensionStatusCode) {
		this.pensionStatusCode = pensionStatusCode;
	}

	public ProcessPensionResponse(int pensionStatusCode) {
		super();
		this.pensionStatusCode = pensionStatusCode;
	}

	public ProcessPensionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
