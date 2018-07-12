package com.assessment.accountservice.dao;

import java.sql.Date;
import java.util.List;

import com.assessment.accountservice.domain.Transaction;

/**
 * @author Akhilesh
 *
 */
public interface TransactionManagerDao {

	public double fetchAccountBalance(long accountId);

	public void creditAmount(double balance, long accountId);

	public void debitBalance(double balance, long accountId);

	public void updateTransaction(int transactionId, double amount, long accountId, Date date, String type);

	public List<Transaction> fetchTransaction(long accountId);
}
