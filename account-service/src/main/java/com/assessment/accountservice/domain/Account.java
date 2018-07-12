package com.assessment.accountservice.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Akhilesh
 *
 */
public class Account {
	//In real scenario account number will be in accordance with business scenario
	private static final AtomicInteger counter = new AtomicInteger(555555555);
	private double balance;
	private int accountId;
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getNewAccountId() {
		return counter.incrementAndGet();
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
