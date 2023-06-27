package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.WalletService;

@RestController
@RequestMapping("api")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	
	
	
}
