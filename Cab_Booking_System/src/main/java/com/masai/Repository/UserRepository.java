package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
