package ru.yandex.practicum.filmorate.annotations.validator;

import ru.yandex.practicum.filmorate.annotations.CorrectReleaseDay;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FilmReleaseDateValidator implements ConstraintValidator<CorrectReleaseDay, LocalDate> {
	@Override
	public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
		return localDate.isAfter(LocalDate.of(1895, 12, 28));
	}

	@Override
	public void initialize(CorrectReleaseDay constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}