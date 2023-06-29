package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

	Optional<Car> findByCarNumber(String carNumber);
}
