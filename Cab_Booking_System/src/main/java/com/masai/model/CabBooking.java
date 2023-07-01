package com.masai.model;

import java.time.LocalDateTime;

import com.masai.model.Enums.CabStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	
	@NotNull
	@NotBlank
	private double distanceInKm;
	
	
	private double totalBill;
	
	@Enumerated(EnumType.STRING)
	private CabStatus cabStatus;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime bookingTime = LocalDateTime.now();
	
	private Integer driverId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private Users user;
	
}
