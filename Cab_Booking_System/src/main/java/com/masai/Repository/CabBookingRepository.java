package com.masai.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CabBooking;

public interface CabBookingRepository extends JpaRepository<CabBooking, Integer>{
	Optional<List<CabBooking>> findByDriverId(Integer driverId);
	Optional<List<CabBooking>> findByUserEmail(String userEmail);
}
