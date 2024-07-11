package com.bank.account.entity;

import com.bank.account.enums.BusinessType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommercialAccount extends BankAccount{

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BusinessType businessType;
	
	@Column(nullable = false)
	private Double totalRevenue;
	
	@Column(nullable = false)
	@Embedded
	private Address officeAddress;
	
	

	public CommercialAccount() {
		super();
	}



	public BusinessType getBusinessType() {
		return businessType;
	}



	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}



	public Double getTotalRevenue() {
		return totalRevenue;
	}



	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}



	public Address getOfficeAddress() {
		return officeAddress;
	}



	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}



	

	
	
	
}
