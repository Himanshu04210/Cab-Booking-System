package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admins;

public interface AdminRepository extends JpaRepository<Admins, Integer>{
	Optional<Admins> findByEmail(String email);
}
