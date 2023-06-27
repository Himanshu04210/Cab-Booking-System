package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.masai.Exception.UserException;
import com.masai.Repository.UserRepository;
import com.masai.model.Users;

public class LogInUserController {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<?> signInUserHandler(Authentication authentication) throws UserException {
		Users loggedInUser = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserException("User not found"));
		
		CurrentSessionUser.email = loggedInUser.getEmail();
		CurrentSessionUser.userId = loggedInUser.getUserId();
		CurrentSessionUser.role = loggedInUser.getRole();
		
		return ResponseEntity.ok(loggedInUser);
	}
	
}
