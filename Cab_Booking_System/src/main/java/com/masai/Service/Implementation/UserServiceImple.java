package com.masai.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Repository.UserRepository;
import com.masai.Service.UserService;
import com.masai.model.Users;
import com.masai.model.Wallet;
import com.masai.model.Enums.WalletStatus;
@Service
public class UserServiceImple implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@Override
	public Users registerUser(Users user) throws UserException {
		if(user == null) throw new UserException("Object is null");
		
 		Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());
 		
 		if(existingUser.isPresent()) throw new UserException("User is already present in database");
 		
 		
 		try {
 			Wallet wallet = user.getWallet();
 			wallet.setWalletBalence(0);
 			wallet.setCreditMoney(0);
 			wallet.setDebitMoney(0);
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
	

}
