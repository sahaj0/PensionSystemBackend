package com.pensionerDetailsMicroservice.Service;

import java.io.IOException;
import java.text.ParseException;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;

public interface PensionerdetailService {
	public PensionerDetail getPensionerDetailByAadhaarNumber(long aadhaarNumber) throws IOException, AadharNumberNotFoundException, NumberFormatException, ParseException;
}
