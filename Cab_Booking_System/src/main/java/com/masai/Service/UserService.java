package com.masai.Service;

import java.util.List;

import com.masai.Exception.UserException;
import com.masai.model.Users;

public interface UserService {

	public Users registerUser(Users user) throws UserException;
	public Users updateUserMobileNumber(String email, String number) throws UserException;
	
	public Users deleteUserByEmail(String email) throws UserException;
	public List<Users> getAllUserList() throws UserException;
	public Users getUserByEmail(String email) throws UserException;
	
	
}
