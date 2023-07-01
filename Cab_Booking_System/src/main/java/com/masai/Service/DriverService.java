package com.masai.Service;

import java.util.List;

import com.masai.Exception.CarException;
import com.masai.Exception.DriverException;
import com.masai.model.Drivers;

public interface DriverService {

	public Drivers registerDriver(Drivers driver) throws DriverException, CarException;
	public Drivers updateDrivercredential(String email, Drivers driver) throws DriverException;
	public Drivers getDriverByDriverId(Integer driverId) throws DriverException;
	public Drivers getDriverByEmail(String email) throws DriverException;
	public Drivers getDriverByLicenceNumber(String licenceNumber) throws DriverException;
	public List<Drivers> getAllDriver() throws DriverException;
	public Drivers deleteDriverByEmail(String email) throws DriverException;
	public List<Drivers> getAllDriverInPages(Integer pageNumber, Integer numberOfRecords) throws DriverException;
	
}
