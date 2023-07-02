package com.masai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Admins {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@NotNull(message = "name cannot be null")
	private String adminName;
	
	@NotNull(message = "email cannot be null")
	@Column(unique = true)
	@Email
	private String email;
	
	@NotNull(message = "password cannot be null")
	@NotBlank
	private String password;
	
	@Embedded
	private Address address;
	
	@Column(insertable = false, updatable = false)
	private String role = "ROLE_ADMIN";
}
