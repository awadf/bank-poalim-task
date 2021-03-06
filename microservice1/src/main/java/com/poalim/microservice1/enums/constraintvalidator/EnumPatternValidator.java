package com.poalim.microservice1.enums.constraintvalidator;

import com.poalim.microservice1.enums.constraint.EnumPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author - FAwad
 */
public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
	private Pattern pattern;

	@Override
	public void initialize(EnumPattern annotation) {
		try {
			pattern = Pattern.compile(annotation.regexp());
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Given regex is invalid", e);
		}
	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		Matcher m = pattern.matcher(value.name());
		return m.matches();
	}
}