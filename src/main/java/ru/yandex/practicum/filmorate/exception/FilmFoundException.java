package ru.yandex.practicum.filmorate.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class FilmFoundException extends RuntimeException {
	public FilmFoundException(String message) {
		super(message);
	}
}
