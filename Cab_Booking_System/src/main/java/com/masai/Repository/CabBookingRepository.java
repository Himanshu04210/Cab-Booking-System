package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CabBooking;

public interface CabBookingRepository extends JpaRepository<CabBooking, Integer>{

}
