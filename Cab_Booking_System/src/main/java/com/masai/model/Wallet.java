package com.masai.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.model.Enums.WalletStatus;

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
	
	private double creditMoney;
	private double debitMoney;
	
	@Enumerated(EnumType.STRING)
	private WalletStatus walletStatus = WalletStatus.ACTIVE;
	
	private LocalDateTime timeStamp;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "transactionId")
	private List<Transactions> transactions;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "userId")
	private Users user;
	
}
