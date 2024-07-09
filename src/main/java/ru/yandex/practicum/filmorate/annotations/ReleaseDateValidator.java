package ru.yandex.practicum.filmorate.annotations;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDateValidation, LocalDate> {
	private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

	public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext cxt) {
		if (releaseDate == null) {
			return true;
		}
		return !releaseDate.isBefore(MIN_RELEASE_DATE);
	}
}