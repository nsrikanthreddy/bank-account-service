package com.bank.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.payload.BankAccountDto;
import com.bank.account.service.BankAccountService;


@RestController
@RequestMapping("/api/bank/accounts")
public class BankAccountController {

	Logger logger= LoggerFactory.getLogger(BankAccountController.class);
	
	@Autowired
	private BankAccountService bankAccountService;

	
	@PostMapping("/createBankAccount")
	public ResponseEntity<BankAccountDto> createUser(@RequestBody BankAccountDto bankAccountDto) {
		
		logger.info("saving customer into db"+bankAccountDto);
		BankAccountDto bankAccount = bankAccountService.createBankAccount(bankAccountDto);
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllBankAccounts")
	public ResponseEntity<List<BankAccountDto>> getAllBankAccounts() {
		
		logger.info("finding All BankAccounts....");
		
		return new ResponseEntity<>(bankAccountService.getAllBankAccounts(), HttpStatus.OK);
	}
	
	@GetMapping("/findBankAccount/{id}")
	public ResponseEntity<BankAccountDto> getBankAccountById(@PathVariable(name = "id") long id) {
		
		logger.info("finding BankAccount by id "+id);
		
		return ResponseEntity.ok(bankAccountService.getBankAccountById(id));
	}
	
	@GetMapping("/findBankAccountsByCustomerId/{customerId}")
	public ResponseEntity<List<BankAccountDto>> getBankAccountsByCustomerId(@PathVariable(name = "customerId") long customerId) {
		
		logger.info("finding BankAccounts by customerId "+customerId);
		
		return ResponseEntity.ok(bankAccountService.getBankAccountsByCustomerId(customerId));
	}
	
	@PutMapping("/updateBankAccount/{accountId}")
	public ResponseEntity<BankAccountDto> updateBankAccount(@RequestBody BankAccountDto bankAccountDto, @PathVariable(name = "accountId") long accountId) {
		
		logger.info("updating BankAccounts by accountId "+accountId);
		
		return ResponseEntity.ok(bankAccountService.updateBankAccount(bankAccountDto, accountId));
	}
}
