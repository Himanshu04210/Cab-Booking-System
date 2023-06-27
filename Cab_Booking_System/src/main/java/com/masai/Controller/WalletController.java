package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.MyRequest;
import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;
import com.masai.Service.WalletService;

@RestController
@RequestMapping("api")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@PatchMapping("/wallets/CreditMoney/")
	public ResponseEntity<?> creditMoneyInWalletHandler(@RequestBody MyRequest myRequest, Authentication authentication) throws WalletException, UserException {
		String totalWalletBalance = walletService.addMoney(authentication.getName(), myRequest.getMoney());
		
		return ResponseEntity.ok(totalWalletBalance);
	}
	
	
	@PatchMapping("/wallets/DebitMoney/")
	public ResponseEntity<?> debitMoneyInWalletHandler(@RequestBody MyRequest myRequest, Authentication authentication) throws WalletException, UserException {
		String totalWalletBalance = walletService.debitMoney(authentication.getName(), myRequest.getMoney());
		
		return ResponseEntity.ok(totalWalletBalance);
	}
	
	
	@GetMapping("/wallets")
	public ResponseEntity<?> addMoneyInWalletHandler(Authentication authentication) throws WalletException, UserException {
		String totalWalletBalance = walletService.totalWalletBalance(authentication.getName());
		
		return ResponseEntity.ok(totalWalletBalance);
	}
	
	
	
}
