package com.assessment.accountservice.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Akhilesh
 *
 */
public class NotNegativeAmountValidator implements ConstraintValidator<NotNegativeAmount, BigDecimal> {

	/*
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(BigDecimal arg0, ConstraintValidatorContext arg1) {

		if (arg0.toPlainString().matches("[0-9]*\\.?[0-9]+") && arg0.doubleValue() > 0) {

			return true;
		} else if(arg0.toPlainString().matches("[0-9]*\\.?[0-9]+") && arg0.intValue() > 0) {

			return true;
		}

		return false;
	}

}
