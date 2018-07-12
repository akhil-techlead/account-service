package com.assessment.accountservice.dao;

import java.util.List;

import com.assessment.accountservice.domain.Account;
import com.assessment.accountservice.domain.Customer;

/**
 * @author Akhilesh
 *
 */
public interface AccountServiceDao {

	public int getTotalAccByCustId(long id);

	public void createAccount(int accountId, long customerid, double amount);

	public List<Customer> getCustomerDetail(long id);

	public void updatePrimaryAccountBalance(long accountId, double amount);

	public Account fetchAccountDetails(long customerId);

}
