package com.masai.Service;

import java.util.List;

import com.masai.Exception.CabBookingException;
import com.masai.Exception.DriverException;
import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;
import com.masai.model.CabBooking;
import com.masai.model.CabResponse;


public interface CabBookingService {
	public CabResponse bookTheCab(String email, CabBooking cabBooking) throws CabBookingException, DriverException, UserException, WalletException;
	
	public List<CabBooking> getCabBookedHistoryByUserEmail(String email) throws CabBookingException, UserException;
	

	public List<CabBooking> getCabBookedHistoryByDriverEmail(String email) throws CabBookingException, DriverException;
	
	
}
