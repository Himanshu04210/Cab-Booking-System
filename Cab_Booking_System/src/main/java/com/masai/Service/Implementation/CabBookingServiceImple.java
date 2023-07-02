package com.masai.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CabBookingException;
import com.masai.Exception.DriverException;
import com.masai.Exception.UserException;
import com.masai.Repository.CabBookingRepository;
import com.masai.Repository.CabResponseRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.Service.CabBookingService;
import com.masai.model.CabBooking;
import com.masai.model.CabResponse;
import com.masai.model.Drivers;
import com.masai.model.Users;
import com.masai.model.Enums.CabStatus;

@Service
public class CabBookingServiceImple implements CabBookingService{

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CabBookingRepository cabBookingRepository;
	
	@Autowired
	private CabResponseRepository cabResponseRepository;
	
	
	@Override
	public CabResponse bookTheCab(String email, CabBooking cabBooking) throws CabBookingException, DriverException, UserException {
		
		Drivers driver = driverRepository.findById(cabBooking.getDriverId()).orElseThrow(() -> new DriverException("Driver not found with this Id"));
		
		Users user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
		
		if(!driver.getLocation().equals(cabBooking.getFromLocation())) throw new DriverException("Drivers location's is different from your location");
		
		
		
		double dis = cabBooking.getDistanceInKm();
		
		double rate = driver.getCar().getRatePerKm();
		
		double totalBill = (double) dis*rate;
		
		cabBooking.setTotalBill(totalBill);
		cabBooking.setCabStatus(CabStatus.BOOKED);
		cabBooking.setUserEmail(email);
		
		CabResponse cabResponse = new CabResponse();
		
		cabResponse.setFromLocation(cabBooking.getFromLocation());
		cabResponse.setToLocation(cabBooking.getToLocation());
		cabResponse.setCabStatus(CabStatus.COMPLETED);
		cabResponse.setTime(String.valueOf(dis*3) + " min");
		cabResponse.setTotalBill(totalBill);
		cabResponse.setDriver(driver);
		cabResponse.setUser(user);
		
		try {
			cabBookingRepository.save(cabBooking);
			return cabResponseRepository.save(cabResponse);
		}
		catch(Exception ex) {
			throw new CabBookingException("Something went wrong");
		}
		
	}


	@Override
	public List<CabBooking> getCabBookedHistoryByUserEmail(String email) throws CabBookingException, UserException {
		
		userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
		
		List<CabBooking> list = cabBookingRepository.findByUserEmail(email).orElseThrow(() -> new CabBookingException("No history available"));
		if(list.isEmpty()) throw new CabBookingException("No history is available");
		
		return list;
	}


	@Override
	public List<CabBooking> getCabBookedHistoryByDriverEmail(String email) throws CabBookingException, DriverException {
		Drivers driver = driverRepository.findByEmail(email).orElseThrow(() -> new DriverException("Driver not found"));
		

		List<CabBooking> list = cabBookingRepository.findByDriverId(driver.getDriverId()).orElseThrow(() -> new CabBookingException("No history available"));
		if(list.isEmpty()) throw new CabBookingException("No history is available");
		
		return list;
	}
	
	


}
