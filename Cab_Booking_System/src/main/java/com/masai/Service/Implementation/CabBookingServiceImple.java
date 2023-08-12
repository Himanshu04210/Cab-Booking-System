package com.masai.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masai.Exception.CabBookingException;
import com.masai.Exception.DriverException;
import com.masai.Exception.UserException;
import com.masai.Exception.WalletException;
import com.masai.Repository.CabBookingRepository;
import com.masai.Repository.CabResponseRepository;
import com.masai.Repository.DriverRepository;
import com.masai.Repository.UserRepository;
import com.masai.Repository.WalletRepository;
import com.masai.Service.CabBookingService;
import com.masai.model.CabBooking;
import com.masai.model.CabResponse;
import com.masai.model.Drivers;
import com.masai.model.Transactions;
import com.masai.model.Users;
import com.masai.model.Wallet;
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
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	@Transactional
	public CabResponse bookTheCab(String email, CabBooking cabBooking) throws CabBookingException, DriverException, UserException, WalletException {
		
		//Check is every thing is right or not 
		
		Drivers driver = driverRepository.findById(cabBooking.getDriverId()).orElseThrow(() -> new DriverException("Driver not found with this Id"));
		
		Users user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
		
		//user.getWallet().setTransactions(null);
		if(!driver.getLocation().toLowerCase().equals(cabBooking.getFromLocation().toLowerCase())) throw new DriverException("Drivers location's is different from your location");
		
		driver.setLocation(cabBooking.getToLocation());
		
		//some business logic is written there
		double dis = cabBooking.getDistanceInKm();
		
		double rate = driver.getCar().getRatePerKm();
		
		double totalBill = (double) dis*rate;
		//Setting remaining thing to the cabBooking object
		cabBooking.setTotalBill(totalBill);
		cabBooking.setCabStatus(CabStatus.BOOKED);
		cabBooking.setUserEmail(email);
		
		//Checking does wallet contain that much money that user is asking for
		Wallet wallet = user.getWallet();
		
		
		double currentMoney = wallet.getWalletBalence() - totalBill;
		
		if(currentMoney < 0) throw new WalletException("Not have suffcient money in your wallet, first add some money");
		
		wallet.setWalletBalence(currentMoney);
		
		//making the transaction
		Transactions transaction = new Transactions();
		transaction.setAmount(totalBill);
		transaction.setDriverEmail(driver.getEmail());
		transaction.setUserEmail(email);
		transaction.setFromLocation(cabBooking.getFromLocation());
		transaction.setToLocation(cabBooking.getToLocation());
		transaction.setTimeStamp(LocalDateTime.now());
		transaction.setWallet(wallet);
		
		
		wallet.getTransactions().add(transaction);
		System.out.println(transaction.getTransactionId());
		
		
		
		//creating the object of CabResponse
		CabResponse cabResponse = new CabResponse();		
		cabResponse.setFromLocation(cabBooking.getFromLocation());
		cabResponse.setToLocation(cabBooking.getToLocation());
		cabResponse.setCabStatus(CabStatus.COMPLETED);
		cabResponse.setTime(String.valueOf(dis*3) + " min");
		cabResponse.setTotalBill(totalBill);
		cabResponse.setDriver(driver);
		cabResponse.setUser(user);
		
		try {
//			transactionRepository.save(transaction);
			walletRepository.save(wallet);
			driverRepository.save(driver);
			cabBookingRepository.save(cabBooking);
			CabResponse response = cabResponseRepository.save(cabResponse);
//			user.getWallet();
			return response;
			
		}
		catch(Exception ex) {
			throw new CabBookingException("Something went wrong " + ex.getLocalizedMessage());
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
