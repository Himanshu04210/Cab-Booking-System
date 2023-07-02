package com.masai.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.Repository.AdminRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;

import com.masai.model.Admins;
import com.masai.model.Drivers;
import com.masai.model.Users;


@Service
public class UserDetailServiceImple implements UserDetailsService{
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		 Optional<Users> optUser = userRepository.findByEmail(username);
		 
		 if(optUser.isPresent()) {

				Users user= optUser.get();
				
				List<GrantedAuthority> authorities= new ArrayList<>();
				SimpleGrantedAuthority sga= new SimpleGrantedAuthority(user.getRole());
				authorities.add(sga);
				
				
				return new User(user.getEmail(), user.getPassword(), authorities);
								
		 }
		 
		 
		 Optional<Drivers> optDriver = driverRepository.findByEmail(username);
		 
		 if(optDriver.isPresent()) {

				Drivers driver= optDriver.get();
				
				List<GrantedAuthority> authorities= new ArrayList<>();
				SimpleGrantedAuthority sga= new SimpleGrantedAuthority(driver.getRole());
				authorities.add(sga);
				
				
				return new User(driver.getEmail(), driver.getPassword(), authorities);
				
				
		 }
		 
		 
		 Optional<Admins> optAdmin = adminRepository.findByEmail(username);
		 
		 if(optAdmin.isPresent()) {

				Admins admin= optAdmin.get();
				
				List<GrantedAuthority> authorities= new ArrayList<>();
				SimpleGrantedAuthority sga= new SimpleGrantedAuthority(admin.getRole());
				authorities.add(sga);
				
				
				return new User(admin.getEmail(), admin.getPassword(), authorities);
				
				
		 }
		 
		
		 throw new BadCredentialsException("User Details not found with this username: "+username);
	}
	
	
	

}
