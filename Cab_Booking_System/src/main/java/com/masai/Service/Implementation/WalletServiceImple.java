package com.masai.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;
import com.masai.Repository.UserRepository;
import com.masai.Repository.WalletRepository;
import com.masai.Service.WalletService;
import com.masai.model.Users;
import com.masai.model.Wallet;
@Service
public class WalletServiceImple implements WalletService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public String addMoney(String email, double creditMoney) throws WalletException, UserException {
		
		Users user = userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
		Wallet wallet = user.getWallet();
		
		System.out.println(wallet.getWalletBalence());
		if(wallet.getWalletStatus().toString().equals("ACTIVE")) {
			
			
			try {
				double balance = wallet.getWalletBalence() + creditMoney;
				wallet.setWalletBalence(balance);
				walletRepository.save(wallet);
				
				return "Total wallet balance is " + balance;
				
			}
			catch (Exception ex) {
				throw new WalletException("some problems occurs " + ex.getMessage() );
			}
			
		}
		else throw new WalletException("User's wallet is not active");
	}

	@Override
	public String debitMoney(String email, double debitMoney) throws WalletException, UserException {
		Users user = userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
		Wallet wallet = user.getWallet();
		
		if(wallet.getWalletStatus().toString().equals("ACTIVE")) {
			
			
			try {
				double balance = wallet.getWalletBalence() - debitMoney;
				if(balance < 0) throw new  WalletException("Sufficent balance is not available, Please first add some money");
				wallet.setWalletBalence(balance);
				walletRepository.save(wallet);
				
				return "Total wallet balance is " + balance;
				
			}
			catch (Exception ex) {
				throw new WalletException();
			}
			
		}
		else throw new WalletException("User's wallet is not active");

	}

	@Override
	public String totalWalletBalance(String email) throws UserException, WalletException {
		Users user = userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
		Wallet wallet = user.getWallet();
		
		if(wallet.getWalletStatus().toString().equals("ACTIVE")) {
			
			return "Total wallet balance is " + wallet.getWalletBalence();
		
		}
		else throw new WalletException("User's wallet is not active");

	}

}
