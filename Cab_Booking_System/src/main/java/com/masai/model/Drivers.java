package com.masai.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Drivers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Column(unique = true)
	private String email;
	private String password;
	
	@NotEmpty
	@Size(min = 10, max = 13, message = "mobile should be a valid mobile number")
	private String mobileNumber;
	
	private LocalDate dob;
	@Min(18)
	@Max(60)
	@NotEmpty
	private Integer age;
	
	@Column(unique = true)
	private String licenceNumber;
	
	private double rating;
	
	
	
	
	
}
