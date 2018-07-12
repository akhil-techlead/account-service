package com.assessment.accountservice.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.assessment.accountservice.domain.Transaction;

/**
 * @author Akhilesh
 *
 */
@Repository
public class TransactionManagerDaoImpl implements TransactionManagerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private final String GET_ACCT_BAL = "select account.balance from account where accountId=?";
	private final String CREDIT_AMOUNT = "update account set balance = balance + ? where accountId=?";
	private final String DEBIT_AMOUNT = "update account set balance = balance - ? where accountId=?";
	private final String UPDATE_TRANSACTION = "insert into transaction values(?,?,?,?,?)";
	private final String GET_TRANSACTION = "select * from transaction where accountId=?";

	/**
	 * @param accountId
	 * @return
	 */
	public double fetchAccountBalance(long accountId) {
		return jdbcTemplate.queryForObject(GET_ACCT_BAL, new Object[] { accountId }, Double.class);
	}

	/**
	 * @param balance
	 * @param accountId
	 */
	public void creditAmount(double balance, long accountId) {
		jdbcTemplate.update(CREDIT_AMOUNT, balance, accountId);
	}

	/**
	 * @param balance
	 * @param accountId
	 */
	public void debitBalance(double balance, long accountId) {
		jdbcTemplate.update(DEBIT_AMOUNT, balance, accountId);
	}

	/*
	 * @see
	 * com.assessment.accountservice.dao.TransactionManagerDao#updateTransaction(
	 * int, double, long, java.sql.Date, java.lang.String)
	 */
	public void updateTransaction(int transactionId, double amount, long accountId, Date date, String type) {
		jdbcTemplate.update(UPDATE_TRANSACTION, transactionId, amount, accountId, date.toString(), type);
	}

	/**
	 * @param accountId
	 * @return
	 */
	public List<Transaction> fetchTransaction(long accountId) {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_TRANSACTION, new Object[] { accountId });
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (Map<String, Object> object : rows) {
			Transaction transaction = new Transaction();
			transaction.setTransactionID((long) object.get("transactionId"));
			transaction.setBalance((double) object.get("balance"));
			transaction.setDate((String) object.get("date"));
			transaction.setType((String) object.get("transactionType"));
			transactions.add(transaction);
		}
		return transactions;

	}
}
