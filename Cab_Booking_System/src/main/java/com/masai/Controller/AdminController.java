package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Service.AdminService;
import com.masai.model.Admins;

@RestController
@RequestMapping("/api")
public class AdminController {

	/*
	 *
	 {
		    "name":"pankaj",
		    "email":"pankaj@gmail.com",
		    "password":"pankaj",
		    "mobileNumber":"9335379832",
		    "address":{
		        "city":"Gurugram",
		        "state":"Delhi"
		    }
			  
	  } 
	 * 
	 * 
	 */
	
	
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/admins/{secretUser}")
	public ResponseEntity<?> registerAdmin(@PathVariable String secretUser, @RequestParam("secretPassword") String secretPassword, @RequestBody Admins admin) throws AdminException {
		if(!(secretUser.toLowerCase().equals("admin") && secretPassword.toLowerCase().equals("admin"))) throw new AdminException("secret username or password is wrong");
		
		
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		Admins existingAdmin = adminService.registerAdmin(admin);
		 
		 return new ResponseEntity<>(existingAdmin, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> deleteAdmin(@PathVariable String email) throws AdminException {
		Admins admin = adminService.deleteAdmin(email);
		
		return ResponseEntity.ok(admin);
	}
	
}
