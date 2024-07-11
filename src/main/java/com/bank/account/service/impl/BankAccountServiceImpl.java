package com.bank.account.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.account.dao.BankAccountRepository;
import com.bank.account.entity.BankAccount;
import com.bank.account.entity.CommercialAccount;
import com.bank.account.entity.PrivateAccount;
import com.bank.account.entity.SavingAccount;
import com.bank.account.enums.AccountType;
import com.bank.account.exception.BankAccountNotFoundException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.extrenal.service.communication.CustomerService;
import com.bank.account.payload.BankAccountDto;
import com.bank.account.payload.CustomerDto;
import com.bank.account.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	Logger logger= LoggerFactory.getLogger(BankAccountServiceImpl.class);
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	
//	@Autowired
//	private CustomerService customerService;
	
	@Override
	public BankAccountDto createBankAccount(BankAccountDto bankAccountDto) {

		BankAccount bankAccount = null;
		
		if(bankAccountDto.getAccountType() == AccountType.SAVING) {
			bankAccount = mapToSavingAccountEntity(bankAccountDto);
			logger.info("saving account "+bankAccount);
		}else if(bankAccountDto.getAccountType() == AccountType.COMMERCIAL) {
			bankAccount = mapToCommercialAccountEntity(bankAccountDto);
			logger.info("Commercial account "+bankAccount);
		} else if(bankAccountDto.getAccountType() == AccountType.PRIVATE) {
			bankAccount = mapToPrivateAccountEntity(bankAccountDto);
			logger.info("Private account "+bankAccount);
		}else {
			
			logger.error("Invalid Account Type selected");
			throw new RuntimeException("Invalid Account Type");
			
		}
		
//		ResponseEntity<CustomerDto> customer = customerService.findCustomer(bankAccountDto.getCustomerId());
//		if(customer.getStatusCodeValue() == 404) {
//			throw new CustomerNotFoundException(bankAccountDto.getCustomerId()+" not an existing customer");
//		}
		
		
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		
		logger.info("new BankAccount saved successfully");
		
		return mapToBankAccountDto(savedBankAccount);
	}
	
	@Override
	public List<BankAccountDto> getAllBankAccounts() {
		
		List<BankAccount> allBankAccounts = bankAccountRepository.findAll();
		
		logger.info("fetched all BankAccounts from db");
		
		return allBankAccounts.stream().map(bankAccount -> mapToBankAccountDto(bankAccount)).collect(Collectors.toList());
	}
	
	@Override
	public BankAccountDto getBankAccountById(Long accountId) {

		BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("BankAccount does not found "));
		
		logger.info(accountId+" BankAccount fetched successfully");
		
		return mapToBankAccountDto(bankAccount);
	}

	@Override
	public List<BankAccountDto> getBankAccountsByCustomerId(Long customerId) {
		
		List<BankAccount> bankAccounts = bankAccountRepository.findByCustomerId(customerId);
		if(bankAccounts == null)	
		   throw new BankAccountNotFoundException("BankAccount does not found ");
		
		logger.info("fetched all BankAccounts for given customerId "+customerId);
		
		return bankAccounts.stream().map(bankAccount -> mapToBankAccountDto(bankAccount)).collect(Collectors.toList());
	}
	
	@Override
	public BankAccountDto updateBankAccount(BankAccountDto bankAccountDto, Long accountId) {

		BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("BankAccount does not found "));
		
		
		bankAccount.setAccountStatus(bankAccountDto.getAccountStatus());
		bankAccount.setCurrentBalance(bankAccountDto.getCurrentBalance());
		
		if(bankAccount.getAccountType() == AccountType.COMMERCIAL) {
			CommercialAccount commercialAccount = (CommercialAccount) bankAccount;
			commercialAccount.setBusinessType(bankAccountDto.getBusinessType());
			commercialAccount.setTotalRevenue(bankAccountDto.getTotalRevenue());
			commercialAccount.setOfficeAddress(bankAccountDto.getOfficeAddress());
			
		} else if(bankAccount.getAccountType() == AccountType.PRIVATE) {
			PrivateAccount privateAccount = (PrivateAccount) bankAccount;
			privateAccount.setWealth(bankAccountDto.getWealth());
			privateAccount.setWealthUnit(bankAccountDto.getWealthUnit());
			
		}
		BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
		
		logger.info(accountId+" BankAccount updated successfully");
		
		return mapToBankAccountDto(updatedBankAccount);
	}
	
	
	private BankAccountDto mapToBankAccountDto(BankAccount bankAccount) {
		
		BankAccountDto bankAccountDto = new BankAccountDto();
		
		bankAccountDto.setAccountId(bankAccount.getAccountId());
		
		bankAccountDto.setCustomerId(bankAccount.getCustomerId());
		
		bankAccountDto.setAccountType(bankAccount.getAccountType());
		
		bankAccountDto.setCurrentBalance(bankAccount.getCurrentBalance());
		
		bankAccountDto.setAccountStatus(bankAccount.getAccountStatus());
		
		if(bankAccount.getAccountType() == AccountType.COMMERCIAL) {
			CommercialAccount commercialAccount = (CommercialAccount) bankAccount;
			bankAccountDto.setBusinessType(commercialAccount.getBusinessType());
			bankAccountDto.setTotalRevenue(commercialAccount.getTotalRevenue());
			bankAccountDto.setOfficeAddress(commercialAccount.getOfficeAddress());
			
		} else if(bankAccount.getAccountType() == AccountType.PRIVATE) {
			PrivateAccount privateAccount = (PrivateAccount) bankAccount;
			bankAccountDto.setWealth(privateAccount.getWealth());
			bankAccountDto.setWealthUnit(privateAccount.getWealthUnit());
			
		}
		
		return bankAccountDto;
	}
	
	private SavingAccount mapToSavingAccountEntity(BankAccountDto bankAccountDto) {
		return mapper.map(bankAccountDto, SavingAccount.class);
	}
	
	private CommercialAccount mapToCommercialAccountEntity(BankAccountDto bankAccountDto) {
		return mapper.map(bankAccountDto, CommercialAccount.class);
	}
	
	private PrivateAccount mapToPrivateAccountEntity(BankAccountDto bankAccountDto) {
		return mapper.map(bankAccountDto, PrivateAccount.class);
	}

	




	
	
	

}
