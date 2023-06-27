package com.masai.Service;

import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;

public interface WalletService {

	public String addMoney(String email, double creditMoney) throws WalletException, UserException;
	
	public String debitMoney(String email, double debitMoney) throws WalletException, UserException;
	
	public String totalWalletBalance(String email) throws UserException, WalletException;
	
}
