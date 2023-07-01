package com.masai.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.CarException;
import com.masai.Exception.DriverException;
import com.masai.Repository.CarRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Service.DriverService;
import com.masai.model.Car;
import com.masai.model.Drivers;


@Service
public class DriverServiceImple implements DriverService{

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	
	public Drivers registerDriver(Drivers driver) throws DriverException, CarException {
		
		Optional<Drivers> opt  = driverRepository.findByEmail(driver.getEmail());
		
		if(opt.isPresent()) throw new DriverException("Driver is already present in database");
		
		Optional<Car> optCar = carRepository.findByCarNumber(driver.getCar().getCarNumber());
		
		if(optCar.isPresent()) throw new CarException("Car is already present in the database");
		
		
		try {
			
			Drivers savedDriver = driverRepository.save(driver);
			
			return savedDriver;
			
		}
		catch(Exception ex) {
			throw new DriverException("Something went wrong because " + ex.getMessage());
		}
		
		
		
	}

	@Override
	public Drivers updateDrivercredential(String email, Drivers driver) throws DriverException {
		
		Drivers existingDriver = driverRepository.findByEmail(email).orElseThrow(() -> new DriverException("No driver present with this email"));
		
		try {
			existingDriver.setAge(driver.getAge());
			existingDriver.setDriverName(driver.getDriverName());
			existingDriver.setLicenceNumber(driver.getLicenceNumber());
			existingDriver.setMobileNumber(driver.getMobileNumber());
			existingDriver.setPassword(driver.getPassword());
			Drivers updatedDriver = driverRepository.save(existingDriver);
			return updatedDriver;
			
		}
		catch(Exception ex) {
			throw new DriverException("Something went wrong because " + ex.getMessage());
		}
	}

	@Override
	public Drivers getDriverByDriverId(Integer driverId) throws DriverException {
		return driverRepository.findById(driverId).orElseThrow(() -> new DriverException("No driver present with this id"));
	}

	@Override
	public Drivers getDriverByEmail(String email) throws DriverException {
		return driverRepository.findByEmail(email).orElseThrow(() -> new DriverException("Driver is not present in the database"));
	}

	@Override
	public List<Drivers> getAllDriver() throws DriverException {
		List<Drivers> drivers = driverRepository.findAll();
		
		if(drivers.isEmpty()) throw new DriverException("No driver is present");
		
		return drivers;
	}

	@Override
	public Drivers deleteDriverByEmail(String email) throws DriverException {
		
		Drivers driver = driverRepository.findByEmail(email).orElseThrow(() -> new DriverException("Driver is not present in the database"));
		
		driverRepository.delete(driver);
		
		return driver;
	}

	@Override
	public Drivers getDriverByLicenceNumber(String licenceNumber) throws DriverException {
		return driverRepository.findByLicenceNumber(licenceNumber).orElseThrow(() -> new DriverException("Driver is not present in the database"));
	}

	@Override
	public List<Drivers> getAllDriverInPages(Integer pageNumber, Integer numberOfRecords) throws DriverException {
		
		Pageable p = PageRequest.of(pageNumber-1, numberOfRecords);
		
		Page<Drivers> page= driverRepository.findAll(p);
		List<Drivers> drivers = page.getContent();
		
		if(drivers.isEmpty()) throw new DriverException("No driver is present in this page");
		
		return drivers;
	}

	@Override
	public List<Drivers> getDriverByLocation(String location) throws DriverException {
		List<Drivers> drivers= driverRepository.findByLocation(location).orElseThrow(() -> new DriverException("No driver present in this location"));
		
		if(drivers.isEmpty()) throw new DriverException("No driver is present in this location");
		
		return drivers;
	}

}
