package com.masai.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.model.Drivers;
import com.masai.model.Users;

public class UserDetailServiceImple implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		 Optional<Users> optUser = userRepository.findByEmail(username);
		 
		 if(optUser.isPresent()) {
			 
		 }
		 
		 
		 Optional<Drivers> optDriver = driverRepository.findByEmail(username);
		 
		 if(optDriver.isPresent()) {
			 
		 }
		 
		 
		 
		 
		
		return null;
	}
	
	
	

}
