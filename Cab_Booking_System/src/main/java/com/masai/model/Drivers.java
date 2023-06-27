package com.masai.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	private String driverName;
	
	@NotEmpty
	@Column(unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@NotBlank
	@Size(max = 50, message = "email length should be less than 50 character")
	private String password;
	
	@NotEmpty
	@Size(min = 10, max = 13, message = "mobile should be a valid mobile number")
	private String mobileNumber;
	
	private LocalDate dob;
	@Min(18)
	@Max(60)
	@NotEmpty
	private Integer age;
	
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String licenceNumber;
	
	@Min(1)
	@Max(10)
	private double rating;
	
	@NotNull
	@NotBlank
	private String location;
	
	@Column(insertable = false, updatable = false)
	private String role = "ROLE_DRIVER";
	
	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Car car;
	
}
