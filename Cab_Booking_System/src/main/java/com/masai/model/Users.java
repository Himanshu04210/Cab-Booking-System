package com.masai.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull
	@NotBlank
	@Size(min = 3 , message = "Name length should not be less than 3")
	@Size(max = 40 , message = "Name length should not be greater than 40")
	private String name;
	

	@NotNull
	@NotBlank
	@Column(unique = true)
	@Size(max = 50, message = "email should not be more than 50 character")
	private String email;
	
	@NotNull
	@NotBlank
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@NotNull
	@NotBlank
	private String mobileNumber;
	
	@Column(updatable = false)
	private String role = "ROLE_USER";
	
	@Column(updatable = false)
	private LocalDateTime dataEntered;
	
	@Embedded
	private Address address;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private  Wallet wallet;

}
