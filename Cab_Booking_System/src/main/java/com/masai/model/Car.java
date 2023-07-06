package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.model.Enums.CarType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer carId;
	
//	@NotNull
//	@NotBlank
	@Column(unique = true)
	private String carNumber;
	
	@Enumerated(EnumType.STRING)
//	@NotNull
//	@NotBlank
	private CarType carType;
	
//	@NotNull
//	@NotBlank
	private Double ratePerKm;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.PERSIST)
	private Drivers driver;
	
}
