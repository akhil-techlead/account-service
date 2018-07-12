package com.assessment.accountservice.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assessment.accountservice.dao.TransactionManagerDao;
import com.assessment.accountservice.domain.AccountTransafer;
import com.assessment.accountservice.domain.Transaction;
import com.assessment.accountservice.modal.TransactionDetailResponse;
import com.assessment.accountservice.modal.TransactionModal;

/**
 * @author Akhilesh
 *
 */
@Service
public class TransactionManagerServiceImpl implements TransactionManagerService{

	@Autowired
	TransactionManagerDao transactionManager;

	/* Transfer amount from one account to another
	 * @see com.assessment.accountservice.service.TransactionManagerService#transaction(com.assessment.accountservice.domain.AccountTransafer)
	 */
	public void transaction(AccountTransafer customerTransaction) {
		double availableBalance = transactionManager.fetchAccountBalance(customerTransaction.getFromAccountId());
		customerTransaction.isTransactionAllowed(availableBalance);
		transactionManager.creditAmount(customerTransaction.getAmount().doubleValue(), customerTransaction.getToAccountId());
		transactionManager.updateTransaction(new Transaction().getTransactionId(),customerTransaction.getAmount().longValue(), customerTransaction.getToAccountId(),new Date(System.currentTimeMillis()), "'credit'");
		transactionManager.debitBalance(customerTransaction.getAmount().doubleValue(), customerTransaction.getFromAccountId());
		transactionManager.updateTransaction(new Transaction().getTransactionId(),customerTransaction.getAmount().longValue(), customerTransaction.getFromAccountId(),new Date(System.currentTimeMillis()), "'debit'");
	}

	@Override
	public TransactionDetailResponse fetchTransactionDetail(int accountId) {	
		List<Transaction> transactions = transactionManager.fetchTransaction(accountId);
		TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
		List<TransactionModal> transactionModal = new ArrayList<>();
		transactions.forEach(t -> {
			TransactionModal transaction = new TransactionModal();
			transaction.setTransactionId(t.getTransactionID());
			transaction.setAmount(t.getBalance());
			transaction.setType(t.getType());
			transaction.setDate(t.getDate());
			transactionModal.add(transaction);
		});
		transactionDetailResponse.setAccountId(accountId);
		transactionDetailResponse.setTotalBalance(transactionManager.fetchAccountBalance(accountId));
		transactionDetailResponse.setTransaction(transactionModal);
		return transactionDetailResponse;
	}

}
