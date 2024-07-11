package com.bank.account.exception;

public class BankAccountNotFoundException extends RuntimeException{

	public BankAccountNotFoundException(String message) {
      super(message);
	}
}
