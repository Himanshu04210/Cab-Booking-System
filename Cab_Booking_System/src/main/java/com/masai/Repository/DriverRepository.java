package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Drivers;

public interface DriverRepository extends JpaRepository<Drivers, Integer>{

	Optional<Drivers> findByEmail(String email);
	Optional<Drivers> findByLicenceNumber(String licenceNumber);
	

}
