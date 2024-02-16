package com.processPensionMicroservice.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class PensionerInput {
	
	
	@NotNull(message="{name.notnull}")
	private String name;
	@NotNull(message="{dob.notnull}")
	@PastOrPresent
	private  @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth;
	@NotNull(message="{pan.notnull}")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "{pan.valid}")
	private String pan;
	@Positive(message="{aadhar.positive}")
	private long aadharNumber;
	@NotNull(message="{pentiontype.notnull}")
	private String pensionType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public PensionerInput(@NotNull(message = "{name.notnull}") String name,
			@NotNull(message = "{dob.notnull}") @PastOrPresent Date dateOfBirth,
			@NotNull(message = "{pan.notnull}") @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "{pan.valid}") String pan,
			@Positive(message = "{aadhar.positive}") long aadharNumber,
			@NotNull(message = "{pentiontype.notnull}") String pensionType) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.aadharNumber = aadharNumber;
		this.pensionType = pensionType;
	}
	public PensionerInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
