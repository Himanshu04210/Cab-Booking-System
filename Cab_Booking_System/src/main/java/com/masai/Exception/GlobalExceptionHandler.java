package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CarException.class)
	public ResponseEntity<?> carExceptionHandler(CarException ce, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(CabBookingException.class)
	public ResponseEntity<?> carExceptionHandler(CabBookingException ce, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<?> carExceptionHandler(DriverException de, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(de.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<?> carExceptionHandler(TransactionException te, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(te.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	


	@ExceptionHandler(UserException.class)
	public ResponseEntity<?> carExceptionHandler(UserException ue, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ue.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	


	@ExceptionHandler(WalletException.class)
	public ResponseEntity<?> carExceptionHandler(WalletException we, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(we.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<?> carExceptionHandler(Exception ex, WebRequest request) {

		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ex.getMessage());
			err.setDetails(request.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	//This method will handle those method which will contains validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> myMathodArguNotValidExceptionHandler(MethodArgumentNotValidException mve) {
    	MyErrorDetails myErorDetails = new MyErrorDetails();
    	
    	myErorDetails.setTimeStamp(LocalDateTime.now());
    	myErorDetails.setMessage("Validation error");
    	myErorDetails.setDetails(mve.getBindingResult().getFieldError().getDefaultMessage());
    	
    	return new ResponseEntity<>(myErorDetails, HttpStatus.BAD_REQUEST);
    	
    }
    
    

    //This exception handler will handle those exception when we try to call that method which is not exist 
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> methodNotFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req) {
    	
    	MyErrorDetails myErorDetails = new MyErrorDetails();
    	
    	myErorDetails.setTimeStamp(LocalDateTime.now());
    	myErorDetails.setMessage(nfe.getMessage());
    	myErorDetails.setDetails(req.getDescription(false));
    	
    	return new ResponseEntity<>(myErorDetails, HttpStatus.BAD_REQUEST);
    	
    	
    }
    
}
