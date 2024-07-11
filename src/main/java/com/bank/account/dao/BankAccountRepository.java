package com.bank.account.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

	List<BankAccount> findByCustomerId(Long customerId);
	
}
