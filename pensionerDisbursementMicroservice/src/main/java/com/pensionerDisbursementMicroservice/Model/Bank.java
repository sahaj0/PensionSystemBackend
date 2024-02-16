package com.pensionerDisbursementMicroservice.Model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Bank {
	
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bank(String name, int acctno, String bankType) {
		super();
		this.name = name;
		this.acctno = acctno;
		this.bankType = bankType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAcctno() {
		return acctno;
	}
	public void setAcctno(int acctno) {
		this.acctno = acctno;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	private String name;
	private int acctno;
	private String bankType;
	
}
