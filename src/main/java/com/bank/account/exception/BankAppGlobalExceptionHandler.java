package com.bank.account.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.account.payload.ErrorInfo;




@RestControllerAdvice
public class BankAppGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(BankAccountNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleResourceNotFoundException(BankAccountNotFoundException exception,
			WebRequest webRequest) {

		ErrorInfo errordetails = new ErrorInfo(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleResourceNotFoundException(Exception exception,
			WebRequest webRequest) {

		ErrorInfo errordetails = new ErrorInfo(new Date(), "Application Error", webRequest.getDescription(false));
		
		return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);

	}
}
