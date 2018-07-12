package com.assessment.accountservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.accountservice.domain.AccountTransafer;
import com.assessment.accountservice.modal.TransactionDetailResponse;
import com.assessment.accountservice.service.TransactionManagerService;
import com.assessment.accountservice.validator.NotEmptyMinSize;

/**
 * @author Akhilesh
 *
 */
@RestController
public class TransactionManagement {

	@Autowired
	private TransactionManagerService transactionManagerService;

	/**
	 * @param transaction
	 * @return
	 */
	@PostMapping("transaction")
	public ResponseEntity<Void> depositAmount(@Valid @RequestBody AccountTransafer accountTransfer) {
		transactionManagerService.transaction(accountTransfer);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * @param accountId
	 * @return
	 */
	@GetMapping("transaction/{accountId}")
	public ResponseEntity<TransactionDetailResponse> fetchTransactionDetails(@NotEmptyMinSize @PathVariable int accountId) {
		transactionManagerService.fetchTransactionDetail(accountId);
		return new ResponseEntity<TransactionDetailResponse>(transactionManagerService.fetchTransactionDetail(accountId),HttpStatus.OK);
	}
}
