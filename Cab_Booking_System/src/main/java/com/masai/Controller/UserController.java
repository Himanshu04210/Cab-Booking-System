package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Service.UserService;
import com.masai.model.Users;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	
	/*
	 
	  
	  {
	  	"name":"Aman",
	  	"email":"aman@gmail.com",
	  	"password":"AmanAstel",
	  	"mobileNumber":"7008009001",
	  	{
	  	  "city":"Kanpur",
	  	  "state":"UP"
	  	}
	  
	  }
	  
	  
	  
	 * 
	 * */
	
	
	
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/hello")
	public ResponseEntity<?> getGreetingMessage() {
		String message = "Welcome to the cab booking application";
		return ResponseEntity.ok(message);
	}
	
	
	//for user
	@PostMapping("/users")
	public  ResponseEntity<?> registerUser(@Valid @RequestBody Users user) throws UserException {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Users savedUser = userService.registerUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	//for user
	@GetMapping("/users/profile")
	public ResponseEntity<?> getUserProfile(Authentication authentication) throws UserException {
		
		String email = authentication.getName();

		Users user = userService.getUserByEmail(email);
		
		return ResponseEntity.ok(user);
	
		
	}
	
	@GetMapping("/users/{email}")
	public ResponseEntity<?> getUserByEmailHandler(@PathVariable String email) throws UserException {
		
		Users user = userService.getUserByEmail(email);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<?>> getAllUserHandler() throws UserException {
		List<Users> users = userService.getAllUserList();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/users/pagination/{numberOfRecord}")
	public ResponseEntity<List<?>> getUserWithPagination(@PathVariable Integer numberOfRecord, @RequestParam Integer page) throws UserException {
		List<Users> users = userService.getAllUsersInPages(page, numberOfRecord);
		
		return ResponseEntity.ok(users);
	}
	
	//for user
	@PatchMapping("/users/update/{mobileNumber}")
	public ResponseEntity<?> updateUserMobileNumberHandler(@PathVariable String mobileNumber, Authentication authentication) throws UserException {
		Users updatedUser = userService.updateUserMobileNumber(authentication.getName(), mobileNumber);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/user/{email}")
	public ResponseEntity<?> deleteUserHandler(@PathVariable String email) throws UserException {
		Users user = userService.deleteUserByEmail(email);
		return ResponseEntity.ok(user);
	}

}
