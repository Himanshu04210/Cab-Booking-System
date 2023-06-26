package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CabBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabBookingId;
	
	@NotNull
	@NotBlank
	private String fromLocation;

	@NotNull
	@NotBlank
	private String toLocation;
	
	
	private LocalDateTime timeStamp = LocalDateTime.now();
	

	@NotNull
	@NotBlank
	private double distanceInKm;
	
	private double totalBill;
	
	
	
	private Integer driverId;
	
	
	
}
