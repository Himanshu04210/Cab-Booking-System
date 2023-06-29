package com.masai.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Drivers updateDrivercredential(Drivers driver) throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drivers getDriverByDriverId(Integer driverId) throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drivers getDriverByEmail(String email) throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Drivers> getAllDriver() throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drivers deleteDriverByEmail(String email) throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drivers getDriverByLicenceNumber(String licenceNumber) throws DriverException {
		// TODO Auto-generated method stub
		return null;
	}

}
