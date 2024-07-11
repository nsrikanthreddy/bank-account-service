package com.bank.account.service;

import java.util.List;

import com.bank.account.payload.BankAccountDto;

public interface BankAccountService {

	BankAccountDto createBankAccount(BankAccountDto BankAccountDto);
	
	List<BankAccountDto> getAllBankAccounts();
	
	BankAccountDto getBankAccountById(Long accountId);
	
	List<BankAccountDto> getBankAccountsByCustomerId(Long customerId);

	BankAccountDto updateBankAccount(BankAccountDto bankAccountDto, Long accountId);
}
