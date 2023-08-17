package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>, PagingAndSortingRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
	
	
}
