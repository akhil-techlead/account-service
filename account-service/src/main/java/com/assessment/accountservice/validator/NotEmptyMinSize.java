package com.assessment.accountservice.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Akhilesh
 *
 */
@Documented
@Constraint(validatedBy = NotEmptyMinSizeValidator.class)
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface NotEmptyMinSize {

	int size() default 11;

	String message() default "Account Number should be of 9 digit";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}