package com.masai.Exception;

public class WalletException extends Exception{

	public WalletException() {
		super("Some internal issue occurs");
	}
	
	public WalletException(String message) {
		super(message);
	}
}
