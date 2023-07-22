package com.masai.Service.Implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Repository.AdminRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.Service.AdminService;
import com.masai.model.Admins;
import com.masai.model.Drivers;
import com.masai.model.Users;

@Service
public class AdminServiceImple implements AdminService{


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	@Override
	public Admins registerAdmin(Admins admin) throws AdminException {
		Optional<Users> existingUser = userRepository.findByEmail(admin.getEmail());
 		if(existingUser.isPresent()) throw new AdminException("User is already present in database");
 		
 		Optional<Drivers> existingDriver = driverRepository.findByEmail(admin.getEmail());
 		if(existingDriver.isPresent()) throw new AdminException("User is already present in database");
 		
 		Optional<Admins> existingAdmin = adminRepository.findByEmail(admin.getEmail());
 		if(existingAdmin.isPresent()) throw new AdminException("User is already present in database");
 		
// 		return adminRepository.save(admin);
 		
 		try {
 			System.out.println(admin.getEmail());
 			Admins savedAdmin = adminRepository.save(admin);
 			return savedAdmin;
 		}catch(Exception ex) {
 			throw new AdminException("Something went wrong " + ex.getMessage());
 		}
	}
	


}
