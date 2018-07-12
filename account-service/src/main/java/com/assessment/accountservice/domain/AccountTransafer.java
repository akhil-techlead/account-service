package com.assessment.accountservice.domain;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import com.assessment.accountservice.exception.InSufficientBalanceException;
import com.assessment.accountservice.validator.NotEmptyMinSize;
import com.assessment.accountservice.validator.NotNegativeAmount;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Akhilesh
 *
 */

public class AccountTransafer {

	@JsonProperty(required = true)
	@NotNull(message = "Amount should be positive")
	@NotNegativeAmount
	private BigDecimal amount;

	@JsonProperty(required = true)
	@NotNull(message = "Account Number should not be null")
	@NotEmptyMinSize
	private long fromAccountId;

	@JsonProperty(required = true)
	@NotNull(message = "Account Number should not be null")
	@NotEmptyMinSize
	private long toAccountId;

	public AccountTransafer() {
	}

	/**
	 * @param amount
	 * @param fromAccountId
	 * @param toAccountId
	 */
	public AccountTransafer(BigDecimal amount, long fromAccountId, long toAccountId) {
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public long getFromAccountId() {
		return fromAccountId;
	}

	public long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @param availBalance
	 * @return
	 */
	public boolean isTransactionAllowed(double availBalance) {
		if (this.getAmount().doubleValue() <= availBalance) {
			return true;
		}

		throw new InSufficientBalanceException("Insufficient Balance", HttpStatus.BAD_REQUEST);
	}
}
