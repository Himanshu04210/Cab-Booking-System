package com.masai.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Repository.AdminRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.Service.UserService;
import com.masai.model.Admins;
import com.masai.model.Drivers;
import com.masai.model.Users;
import com.masai.model.Wallet;
import com.masai.model.Enums.WalletStatus;
@Service
public class UserServiceImple implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Users registerUser(Users user) throws UserException {
		if(user == null) throw new UserException("Object is null");
		
 		Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());
 		
 		if(existingUser.isPresent()) throw new UserException("User is already present in database");
 		
 		Optional<Drivers> existingDriver = driverRepository.findByEmail(user.getEmail());
 		if(existingDriver.isPresent()) throw new UserException("User is already present in database");
 		
 		Optional<Admins> existingAdmin = adminRepository.findByEmail(user.getEmail());
 		if(existingAdmin.isPresent()) throw new UserException("User is already present in database");
 		
 		
 		try {
 			Wallet wallet = user.getWallet();
 			wallet.setWalletBalence(0);
 			wallet.setCreditMoney(0);
 			wallet.setDebitMoney(0);
 			wallet.setTimeStamp(LocalDateTime.now());
 			wallet.setWalletStatus(WalletStatus.ACTIVE);
 			Users savedUser = userRepository.save(user);
 			return savedUser;
 		}
 		catch(Exception ex) {
 			throw new UserException("Something went wrong " + ex.getMessage());
 		}
 		
	}




	@Override
	public Users updateUserMobileNumber(String email, String number) throws UserException {

		if(email == null) throw new UserException("email is null");
 		Users existingUser = userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
		existingUser.setMobileNumber(number);
		
		try {
			return userRepository.save(existingUser);
		}
		catch(Exception ex) {
			throw new UserException("Something went wrong "  + ex.getMessage());
		}
	}




	@Override
	public Users deleteUserByEmail(String email) throws UserException {
		
		Users existingUser = userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
		try {
			userRepository.delete(existingUser);
			return existingUser;
		}
		catch(Exception ex) {
			throw new UserException("Something went wrong "  + ex.getMessage());
		}
	}




	@Override
	public List<Users> getAllUserList() throws UserException {
		List<Users> userList = userRepository.findAll();
		
		if(userList.isEmpty()) throw new UserException("No user is available");
		
		return userList;
	}




	@Override
	public Users getUserByEmail(String email) throws UserException {
		return userRepository.findByEmail(email)
 				.orElseThrow(() -> new UserException("User is not exist with this emailId") );
 		
	}




	@Override
	public List<Users> getAllUsersInPages(Integer pageNumber, Integer numberOfRecords) throws UserException {
		Pageable p = PageRequest.of(pageNumber-1, numberOfRecords);
		
		Page<Users> page= userRepository.findAll(p);
		
		List<Users> users = page.getContent();
		if(users.isEmpty()) throw new UserException("No user present in this page");
		
		return users;
	}
	

}
