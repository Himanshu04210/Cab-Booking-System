package com.masai.Exception;

public class DriverException extends Exception{

	public DriverException() {
		super("Some internal issue occurs");
	}
	
	public DriverException(String message) {
		super(message);
	}
}
