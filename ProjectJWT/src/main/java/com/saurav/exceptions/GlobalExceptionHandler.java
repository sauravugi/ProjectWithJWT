package com.saurav.exceptions;

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
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(AdminException ex , WebRequest req){
		
		ErrorDetails er = new ErrorDetails();
		
		er.setTimestamp(LocalDateTime.now());
		er.setMessage(ex.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorDetails> orderExceptionHandler(OrderException ex , WebRequest req){
		
		ErrorDetails er = new ErrorDetails();
		
		er.setTimestamp(LocalDateTime.now());
		er.setMessage(ex.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}

	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException ex , WebRequest req){
		
		ErrorDetails er = new ErrorDetails();
		
		er.setTimestamp(LocalDateTime.now());
		er.setMessage(ex.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ex , WebRequest req){
		
		ErrorDetails er = new ErrorDetails();
		
		er.setTimestamp(LocalDateTime.now());
		er.setMessage(ex.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	 @ExceptionHandler(NoHandlerFoundException.class)
	 public ResponseEntity<ErrorDetails> notFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req ){ 
	  ErrorDetails err = new ErrorDetails();
	   
	  err.setTimestamp(LocalDateTime.now());
	  err.setMessage(nfe.getMessage());
	  err.setDetails(req.getDescription(false));
	   
	  return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	 }
	  
	 //wrong uri exception
	  
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ErrorDetails> MANVExceptionHandler(MethodArgumentNotValidException me) {
	  ErrorDetails err = new ErrorDetails();
	   
	  err.setTimestamp(LocalDateTime.now());
	  err.setMessage("Validation Error");
	  err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
	   
	  return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	 }
	  
	 //for other exception
	  
	 @ExceptionHandler(Exception.class) 
	 public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req) { 
	  ErrorDetails err = new ErrorDetails();
	   
	  err.setTimestamp(LocalDateTime.now());
	  err.setMessage(e.getMessage());
	  err.setDetails(req.getDescription(false));
	   
	  return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	 }


}
