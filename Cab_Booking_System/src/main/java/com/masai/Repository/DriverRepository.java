package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Drivers;
import com.masai.model.Users;

public interface DriverRepository extends JpaRepository<Drivers, Integer>{

	Optional<Users> findByEmail(String email);

}
