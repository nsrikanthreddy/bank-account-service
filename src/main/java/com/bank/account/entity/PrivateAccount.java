package com.bank.account.entity;

import com.bank.account.enums.WealthUnit;

import jakarta.persistence.Column;
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
public class PrivateAccount extends BankAccount{

	@Column(nullable = false)
	private Double wealth;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private WealthUnit wealthUnit;
	
	

	public PrivateAccount() {
		super();
	}



	public Double getWealth() {
		return wealth;
	}



	public void setWealth(Double wealth) {
		this.wealth = wealth;
	}



	public WealthUnit getWealthUnit() {
		return wealthUnit;
	}



	public void setWealthUnit(WealthUnit wealthUnit) {
		this.wealthUnit = wealthUnit;
	}

	
	
}
