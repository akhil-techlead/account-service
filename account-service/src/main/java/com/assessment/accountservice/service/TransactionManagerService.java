package com.assessment.accountservice.service;

import com.assessment.accountservice.domain.AccountTransafer;
import com.assessment.accountservice.modal.TransactionDetailResponse;

/**
 * @author Akhilesh
 *
 */
public interface TransactionManagerService {

	public void transaction(AccountTransafer customerTransaction);

	public TransactionDetailResponse fetchTransactionDetail(int accountId);

}
