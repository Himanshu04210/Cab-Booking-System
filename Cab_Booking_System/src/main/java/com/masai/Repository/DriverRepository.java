package com.masai.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Drivers;

public interface DriverRepository extends JpaRepository<Drivers, Integer>, PagingAndSortingRepository<Drivers, Integer>{

	Optional<Drivers> findByEmail(String email);
	Optional<Drivers> findByLicenceNumber(String licenceNumber);
	Optional<List<Drivers>> findByLocation(String location);

}
