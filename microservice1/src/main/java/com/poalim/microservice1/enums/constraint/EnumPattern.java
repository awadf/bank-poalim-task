package com.poalim.microservice1.enums.constraint;

import com.poalim.microservice1.enums.constraintvalidator.EnumPatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author - FAwad
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
	String regexp();
	String message() default "Enum must match \"{regexp}\"";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}