package ru.yandex.practicum.filmorate.exception;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class UserValidationException extends ConstraintViolationException {
	public UserValidationException(String message, Set<ConstraintViolation<User>> violations) {

		super(message, violations);
	}
}