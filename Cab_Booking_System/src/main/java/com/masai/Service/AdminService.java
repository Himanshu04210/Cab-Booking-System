package com.masai.Service;

import com.masai.Exception.AdminException;
import com.masai.model.Admins;

public interface AdminService {
	public Admins registerAdmin(Admins admin) throws AdminException;
}
