package com.assessment.accountservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Akhilesh
 *
 */
public class NotEmptyMinSizeValidator implements ConstraintValidator<NotEmptyMinSize, Long> {

	/*
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Long arg0, ConstraintValidatorContext arg1) {
		return String.valueOf(arg0).length() == 9 ? true : false;
	}

}
