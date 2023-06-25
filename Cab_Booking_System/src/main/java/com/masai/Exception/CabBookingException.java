package com.masai.Exception;

public class CabBookingException extends Exception{

	
	public CabBookingException() {
		super("Some internal issu occurs");
	}
	
	public CabBookingException(String message) {
		super(message);
	}
}
