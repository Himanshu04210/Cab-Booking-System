package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CabBookingException;
import com.masai.Exception.DriverException;
import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;
import com.masai.Service.CabBookingService;
import com.masai.model.CabBooking;
import com.masai.model.CabResponse;

@RestController
@RequestMapping("/api")
public class CabBookingController {

	@Autowired
	private CabBookingService cabBookingService;
	
	
	@PostMapping("/cabBooking")
	public ResponseEntity<?> bookCabHandler(Authentication authentication, @RequestBody CabBooking cabBooking) throws CabBookingException, DriverException, UserException, WalletException {
	
		String email = authentication.getName();
		
		CabResponse cabResponse = cabBookingService.bookTheCab(email, cabBooking);
		
		return ResponseEntity.ok(cabResponse);
	}
	
	
	@GetMapping("/cabBooking/user")
	public ResponseEntity<?> getCabBookedHistoryByUserEmail(Authentication authentication) throws CabBookingException, UserException {

		String email = authentication.getName();
		
		List<CabBooking> cabBookings = cabBookingService.getCabBookedHistoryByUserEmail(email);
		
		return ResponseEntity.ok(cabBookings);
	}
	
	
	@GetMapping("/CabBooking/driver")
	public ResponseEntity<?> getCabBookedHistoryByDriverEmail(Authentication authentication) throws CabBookingException, DriverException {

		String email = authentication.getName();
		
		List<CabBooking> cabBookings = cabBookingService.getCabBookedHistoryByDriverEmail(email);
		
		return ResponseEntity.ok(cabBookings);
	}
	
	
	
}
