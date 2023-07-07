package com.masai.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Repository.AdminRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.model.Admins;
import com.masai.model.Drivers;
import com.masai.model.Users;
@RestController
@RequestMapping("/api")
public class LogInUserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<?> signInUserHandler(Authentication authentication) throws UserException {
		
		
		Optional<Users> user = userRepository.findByEmail(authentication.getName());
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());	
		}
		
		Optional<Drivers> driver = driverRepository.findByEmail(authentication.getName());
		

		if(driver.isPresent()) {
			return ResponseEntity.ok(driver.get());	
		}
		
		Optional<Admins> admin = adminRepository.findByEmail(authentication.getName());
		

		if(admin.isPresent()) {
			return ResponseEntity.ok(admin.get());	
		}
		
		throw new BadCredentialsException("Credintial are not matched....");
		
		
	}
	
}
