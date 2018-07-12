package com.assessment.accountservice.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.assessment.accountservice.dao.AccountServiceDao;
import com.assessment.accountservice.dao.TransactionManagerDao;
import com.assessment.accountservice.domain.Customer;
import com.assessment.accountservice.domain.Transaction;
import com.assessment.accountservice.exception.AccountEnrollmentLimitReachedException;
import com.assessment.accountservice.exception.CustomerNotFoundException;
import com.assessment.accountservice.modal.AccountResponse;
import com.assessment.accountservice.modal.CustomerDetail;
import com.assessment.accountservice.domain.Account;
import com.assessment.accountservice.domain.Amount;

/**
 * @author Akhilesh
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountServiceDao accountService;

	@Autowired
	TransactionManagerDao transactionManagerDao;

	/**
	 * @param amount
	 * @param customerId
	 */
	public void createAccountForExistingCustomer(Amount amount, long customerId) {
		if (isEligibleForNewAccount(customerId)) {
			Account account = accountService.fetchAccountDetails(customerId);
			Amount balance = amount.minus(account.getBalance());
			final int secondaryAccount = account.getNewAccountId();
			accountService.createAccount(secondaryAccount, customerId, amount.getAmount());
			accountService.updatePrimaryAccountBalance(account.getAccountId(), balance.getAmount());
			transactionManagerDao.updateTransaction(new Transaction().getTransactionId(), amount.getAmount(),
					secondaryAccount, new Date(System.currentTimeMillis()), "'credit'");
			transactionManagerDao.updateTransaction(new Transaction().getTransactionId(), amount.getAmount(),
					account.getAccountId(), new Date(System.currentTimeMillis()), "'debit'");
		}
	}

	/**
	 * @param customerId
	 * @return
	 */
	public CustomerDetail getCustomerDetail(long customerId) {
		List<Customer> customers = accountService.getCustomerDetail(customerId);
		CustomerDetail customerDetail = new CustomerDetail();
		List<AccountResponse> accounts = new ArrayList<>();
		customerDetail.setCustomerId(customerId);
		if (customers.get(0) != null) {
			customerDetail.setName(customers.get(0).getName());
			customerDetail.setSurname(customers.get(0).getSurname());
		}
		customers.forEach(customer -> {
			AccountResponse account = new AccountResponse();
			account.setAccountId(String.valueOf(customer.getAccountId()));
			account.setBalance(String.format("%.2f", customer.getBalance()));
			account.setAccountType(customer.getAccountType());
			accounts.add(account);
		});
		customerDetail.setAccountDetail(accounts);
		return customerDetail;
	}

	/**
	 * @param customerId
	 * @return
	 */
	public boolean isEligibleForNewAccount(long customerId) {
		int totalAccounts = accountService.getTotalAccByCustId(customerId);
		if (totalAccounts == 0) {
			throw new CustomerNotFoundException("Customer Id Does Not Exist", HttpStatus.NOT_FOUND);
		} else if (totalAccounts == 2) {
			throw new AccountEnrollmentLimitReachedException("Account Already Exist", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return true;
		}
	}
}
