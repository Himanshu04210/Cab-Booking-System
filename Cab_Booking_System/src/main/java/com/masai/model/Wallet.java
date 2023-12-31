package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.model.Enums.WalletStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter

public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;
	
	
	private double walletBalence;
	
	
	@Enumerated(EnumType.STRING)
	private WalletStatus walletStatus = WalletStatus.ACTIVE;
	
	private LocalDateTime timeStamp;
	
	
	// Unidirectional one-to-many relationship from Wallet to Transactions
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "wallet")
    //@JoinColumn(name = "transactionId") // This specifies the foreign key column in Transactions table
    private List<Transactions> transactions = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "userId")
	private Users user;
	
	
}
