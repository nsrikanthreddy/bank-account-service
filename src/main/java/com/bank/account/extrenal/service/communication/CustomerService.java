package com.bank.account.extrenal.service.communication;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.account.payload.CustomerDto;

//@Service
//@FeignClient(name = "customer-service/api/bank/customers")
public interface CustomerService {

	@GetMapping(value = "/findCustomer/{id}")
	public ResponseEntity<CustomerDto> findCustomer(@PathVariable(name = "id") Long id);
	
}
