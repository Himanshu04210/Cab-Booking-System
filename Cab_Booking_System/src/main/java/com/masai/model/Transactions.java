package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;
	
	
	private LocalDateTime timeStamp;
	
	private String fromLocation;
	private String toLocation;
	
	private double amount;
	
	
	private String userEmail;
	
	private String driverEmail;

	
	
}
