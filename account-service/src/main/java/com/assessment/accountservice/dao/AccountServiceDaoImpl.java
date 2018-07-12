package com.assessment.accountservice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.assessment.accountservice.domain.Account;
import com.assessment.accountservice.domain.Customer;

/**
 * @author Akhilesh
 *
 */
@Repository
public class AccountServiceDaoImpl implements AccountServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private final String GET_TOTAL_ACC_BY_ID = "SELECT count(*) from account where customerId=?";
	private final String CREATE_ACCOUNT = "INSERT INTO account (accountid, balance, accountType, customerid) VALUES (?,?,?,?)";
	private final String GET_CUSTOMER_DETAIL = "select customers.name, customers.surname, account.balance, account.accountid, account.accountType from customers, account where customers.customerid = ?";
	private final String UPDATE_PRIMARY_ACCT_BAL = "UPDATE account set balance = ? where accountid = ?";
	private final String FETCH_ACCT_DETAILS = "SELECT account.accountId, account.balance from account where customerId=? and accountType='primary'";

	/**
	 * @param id
	 * @return
	 */
	public int getTotalAccByCustId(long id) {
		return jdbcTemplate.queryForObject(GET_TOTAL_ACC_BY_ID, new Object[] { id }, Integer.class);
	}

	/**
	 * @param account
	 * @param customerid
	 * @param amount
	 */
	public void createAccount(int accountId, long customerid, double amount) {
		jdbcTemplate.update(CREATE_ACCOUNT, accountId, amount, "secondary", customerid);
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Customer> getCustomerDetail(long id) {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_CUSTOMER_DETAIL, new Object[] { id });
		List<Customer> customers = new ArrayList<Customer>();
		for(Map<String, Object> object : rows) {
			Customer customer = new Customer();
			customer.setCustomerId(String.valueOf(id));
			customer.setName((String)object.get("name"));
			customer.setSurname((String)object.get("surName"));
			customer.setBalance((double)object.get("balance"));
			customer.setAccountId(String.valueOf(object.get("accountId")));
			customer.setAccountType((String)object.get("accountType"));
			customers.add(customer);
		}
		return customers;
	}

	/**
	 * @param accountId
	 * @param amount
	 */
	public void updatePrimaryAccountBalance(long accountId, double amount) {

		jdbcTemplate.update(UPDATE_PRIMARY_ACCT_BAL, amount, accountId);

	}

	/**
	 * @param customerId
	 * @return
	 */
	public Account fetchAccountDetails(long customerId) {
		return (Account) jdbcTemplate.queryForObject(FETCH_ACCT_DETAILS, new Object[] { customerId },
				new BeanPropertyRowMapper<Account>(Account.class));
	}
}
