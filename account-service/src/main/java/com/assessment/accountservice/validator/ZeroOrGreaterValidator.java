package com.assessment.accountservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZeroOrGreaterValidator implements ConstraintValidator<ZeroOrGreater, Double> {

	@Override
	public boolean isValid(Double arg0, ConstraintValidatorContext arg1) {
		return arg0 >= 0 ? true : false;
	}

}
