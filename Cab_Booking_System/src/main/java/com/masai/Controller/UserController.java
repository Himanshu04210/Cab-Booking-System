package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Service.UserService;
import com.masai.model.Users;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/users")
	public  ResponseEntity<?> registerUser(@RequestBody Users user) throws UserException {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Users savedUser = userService.registerUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
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
	
	
	public ResponseEntity<?> deleteUserHandler(String email) throws UserException {
		Users user = userService.deleteUserByEmail(email);
		return ResponseEntity.ok(user);
	}

}
