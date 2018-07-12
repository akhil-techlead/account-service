package com.assessment.accountservice.modal;

import java.util.List;

/**
 * @author Akhilesh
 *
 */
public class CustomerDetail {

	private long customerId;
	private String name;
	private String surname;
	private List<AccountResponse> accountDetail;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<AccountResponse> getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(List<AccountResponse> accountDetail) {
		this.accountDetail = accountDetail;
	}
}
