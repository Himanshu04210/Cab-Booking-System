package com.masai.Service;

import com.masai.Exception.CabBookingException;
import com.masai.Exception.DriverException;
import com.masai.Exception.UserException;
import com.masai.model.CabBooking;
import com.masai.model.CabResponse;


public interface CabBookingService {
	public CabResponse bookTheCab(String email, CabBooking cabBooking) throws CabBookingException, DriverException, UserException;
	
}
