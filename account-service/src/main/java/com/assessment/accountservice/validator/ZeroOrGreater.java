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
@Constraint(validatedBy = ZeroOrGreaterValidator.class)
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface ZeroOrGreater {
	

	int size() default 11;

	String message() default "Initial Amount should be Zero or Greater than Zero";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
