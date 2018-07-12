package com.assessment.accountservice.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotNegativeAmountValidator.class)
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface NotNegativeAmount {
	
	int size() default 11;

	String message() default "Amount should be positive and greater than 0";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
