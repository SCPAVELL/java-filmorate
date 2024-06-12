package ru.yandex.practicum.filmorate.annotations.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotations.CorrectLogin;

public class LoginValidator implements ConstraintValidator<CorrectLogin, String> {
	@Override
	public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
		if (login == null) {
			return true;
		}
		return !(login.contains(" "));
	}
}
