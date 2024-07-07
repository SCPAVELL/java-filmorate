package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmValidationException extends ConstraintViolationException {
	public FilmValidationException(String message, Set<ConstraintViolation<Film>> violations) {
		super(message, violations);
	}
}
