package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Drivers;

public interface DriverRepository extends JpaRepository<Drivers, Integer>{

}
