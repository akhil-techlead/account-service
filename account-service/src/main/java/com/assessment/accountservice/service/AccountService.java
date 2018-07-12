package com.assessment.accountservice.service;

import com.assessment.accountservice.domain.Amount;
import com.assessment.accountservice.modal.CustomerDetail;

/**
 * @author Akhilesh
 *
 */
public interface AccountService {

	public void createAccountForExistingCustomer(Amount amount, long customerId);

	public CustomerDetail getCustomerDetail(long customerId);

}
