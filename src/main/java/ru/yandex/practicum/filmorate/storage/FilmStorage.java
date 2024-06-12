package ru.yandex.practicum.filmorate.storage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yandex.practicum.filmorate.model.Film;

@Component
@Getter
@EqualsAndHashCode
@ToString
public class FilmStorage {
	private final Map<Integer, Film> films = new HashMap<>();
	private static int id;

	public int generateId() {
		return ++id;
	}

	public Film create(Film film) {
		int newTaskId = generateId();
		film.setId(newTaskId);
		films.put(newTaskId, film);
		return film;
	}

	public Film update(Film film) {
		films.put(film.getId(), film);
		return film;
	}
}