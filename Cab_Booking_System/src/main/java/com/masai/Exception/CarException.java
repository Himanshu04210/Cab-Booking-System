package com.masai.Exception;


public class CarException extends Exception {
	public CarException() {
		super("Something went wrong");
	}
	
	public CarException(String message) {
		super(message);
	}
}
