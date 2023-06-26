package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CabBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabBookingId;
	
	private String fromLocation;
	private String toLocation;
	
	private LocalDateTime timeStamp;
	
	private double distanceInKm;
	
	private double totalBill;
	
	
	
	private Drivers driver;
	
	
	
}
