package com.assessment.accountservice.modal;

import java.util.List;

/**
 * @author Akhilesh
 *
 */
public class TransactionDetailResponse {

	private long accountId;
	private double totalBalance;
	private List<TransactionModal> transaction;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public List<TransactionModal> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<TransactionModal> transaction) {
		this.transaction = transaction;
	}

}
