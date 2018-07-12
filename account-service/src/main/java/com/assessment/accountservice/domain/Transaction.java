package com.assessment.accountservice.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

	private static final AtomicInteger counter = new AtomicInteger(1111);

	private long transactionID;
	private double balance;
	private String date;
	private String type;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static AtomicInteger getCounter() {
		return counter;
	}

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public int getTransactionId() {
		Random rand = new Random();
		return rand.nextInt(50) + counter.incrementAndGet();
	}

}
