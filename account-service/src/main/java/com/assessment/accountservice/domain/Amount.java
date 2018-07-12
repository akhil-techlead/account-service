package com.assessment.accountservice.domain;

import org.springframework.http.HttpStatus;

import com.assessment.accountservice.exception.InSufficientBalanceException;

/**
 * @author Akhilesh
 *
 */
public class Amount {
	
		private double amount;

		/**
		 * @param result
		 */
		public Amount(double result) {
			this.amount = result;
		}

		public Amount() {
			// TODO Auto-generated constructor stub
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
		
	    /**
	     * @param amount
	     * @return
	     */
	    public Amount minus(final double amount){
		        final double result = amount - this.getAmount();
		        if(result <= 0) {
		        	throw new InSufficientBalanceException("Insufficient Fund", HttpStatus.BAD_REQUEST);
		        }
		        return new Amount(result);
	    }

}
