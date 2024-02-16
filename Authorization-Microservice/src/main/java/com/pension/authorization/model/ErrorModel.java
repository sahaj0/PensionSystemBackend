package com.pension.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
   
	public ErrorModel() {
		// super();
		// TODO Auto-generated constructor stub
	}
	public ErrorModel(int errorId, String errorText, String errorLogTime) {
		super();
		this.errorId = errorId;
		this.errorText = errorText;
		this.errorLogTime = errorLogTime;
	}
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getErrorLogTime() {
		return errorLogTime;
	}
	public void setErrorLogTime(String errorLogTime) {
		this.errorLogTime = errorLogTime;
	}
	private int errorId;
	private String errorText;
	private String errorLogTime;
}
