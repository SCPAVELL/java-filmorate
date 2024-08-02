package ru.yandex.practicum.filmorate.model.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReleaseDateValidator implements ConstraintValidator<ValidReleaseDate, LocalDate> {
	@Override
	public void initialize(ValidReleaseDate constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return value == null || value.isAfter(LocalDate.of(1895, 12, 28));
	}
}
