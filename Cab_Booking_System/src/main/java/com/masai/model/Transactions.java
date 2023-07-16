package com.masai.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;
	
	
}
