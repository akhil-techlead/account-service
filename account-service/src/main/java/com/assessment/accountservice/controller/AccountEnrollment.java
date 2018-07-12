package com.assessment.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.accountservice.domain.Amount;
import com.assessment.accountservice.modal.CustomerDetail;
import com.assessment.accountservice.service.AccountService;
import com.assessment.accountservice.validator.NotEmptyMinSize;
import com.assessment.accountservice.validator.ZeroOrGreater;

/**
 * @author Akhilesh
 *
 */
@Validated
@RestController
@RequestMapping(produces = "application/json")
public class AccountEnrollment {

	@Autowired
	private AccountService accountService;

	/**
	 * @param customerId
	 * @param initialCredit
	 * @return
	 */
	@PostMapping("customer/{customerId}/amount/{initialCredit:.+}")
	public ResponseEntity<Void> createSecondaryAccount(
			@NotEmptyMinSize(message="Customer Id should be of 9 digits") @PathVariable("customerId") long customerId,
			@PathVariable("initialCredit") @ZeroOrGreater double initialCredit) {
		accountService.createAccountForExistingCustomer(new Amount(initialCredit),customerId);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * @param customerId
	 * @return
	 */
	@GetMapping("customer/{customerId}")
	public ResponseEntity<CustomerDetail> getCustomer(
			@NotEmptyMinSize(message = "Customer Id should be of 9 digits") @PathVariable("customerId") long customerId) {
		return new ResponseEntity<CustomerDetail>(accountService.getCustomerDetail(customerId), HttpStatus.OK);
	}
}
