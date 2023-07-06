package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.oauth2.login.AuthorizationEndpointDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CarException;
import com.masai.Exception.DriverException;
import com.masai.Service.DriverService;
import com.masai.model.Drivers;

@RestController
@RequestMapping("/api")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/drivers")
	public ResponseEntity<?> registerDriver(@RequestBody Drivers driver) throws DriverException, CarException {
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		Drivers savedDriver = driverService.registerDriver(driver);
		
		return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/drivers/profile")
	public ResponseEntity<?> getDriverDetail(Authentication authentication) throws DriverException {
		String email = authentication.getName();
		
		Drivers driver = driverService.getDriverByEmail(email);
		
		return ResponseEntity.ok(driver);
		
	}
	
	@GetMapping("/drivers/email/{email}")
	public ResponseEntity<?> getDriverByEmail(@PathVariable String email) throws DriverException {
		
		Drivers driver = driverService.getDriverByEmail(email);
		
		return ResponseEntity.ok(driver);
		
	}
	
	@GetMapping("/drivers/location")
	public ResponseEntity<List<?>> getDriversByLocation(@PathVariable String location) throws DriverException {
		List<Drivers> drivers = driverService.getDriverByLocation(location);
		
		return ResponseEntity.ok(drivers);
	
	}
	
	
	@GetMapping("/drivers/id/{driverId}")
	public ResponseEntity<?> getDriverByDriverId(@PathVariable Integer driverId) throws DriverException {

		Drivers driver = driverService.getDriverByDriverId(driverId);
		
		return ResponseEntity.ok(driver);
		
	}
	
	@GetMapping("/drivers/licenseNumber")
	public ResponseEntity<?> getDriverByLicenseNumber(@RequestParam String LicenceNumber) throws DriverException {

		Drivers driver = driverService.getDriverByLicenceNumber(LicenceNumber);
		
		return ResponseEntity.ok(driver);
		
	}
	
	
	@GetMapping("/drivers")
	public ResponseEntity<List<?>> getAllDriver() throws DriverException {
		List<Drivers> drivers = driverService.getAllDriver();
		
		return ResponseEntity.ok(drivers);
	}
	
	@GetMapping("/drivers/pagination/{numberOfRecord}")
	public ResponseEntity<List<?>> getDriverWithPagination(@PathVariable Integer numberOfRecord, @RequestParam Integer page) throws DriverException {
		List<Drivers> drivers = driverService.getAllDriverInPages(page, numberOfRecord);
		
		return ResponseEntity.ok(drivers);
	}
	
	
	@PatchMapping("/drivers")
	public ResponseEntity<?> updateDrivercredential(Authentication authentication, @RequestBody Drivers driver) throws DriverException {
		String email = authentication.getName();
		Drivers updatedCredential = driverService.updateDrivercredential(email, driver);
		
		return ResponseEntity.ok(updatedCredential);
	}
	
	@DeleteMapping("/drivers")
	public ResponseEntity<?> deleteDriverByEmail(@RequestParam String email) throws DriverException {
		Drivers driver = driverService.deleteDriverByEmail(email);
		
		return ResponseEntity.ok(driver);
	}
	
}
