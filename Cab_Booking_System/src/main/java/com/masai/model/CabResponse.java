package com.masai.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CabResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer rosponseId;
	
	private String fromLocation;
	
	private String toLocation;
	
	private double totalBill;
	
	private String time;
	
	@Enumerated(EnumType.STRING)
	private CabStatus cabStatus;
	
	@Column(updatable = false)
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	@OneToOne
	@JoinColumn(name = "userId")
	private Users user;
	
	
	@OneToOne
	@JoinColumn(name = "driverId")
	private Drivers driver;
}
