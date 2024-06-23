package ru.yandex.practicum.filmorate.model.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

@Component
@Slf4j
@AllArgsConstructor
public class FilmService {
	private final FilmStorage filmStorage;
	private final UserStorage userStorage;
	private static final LocalDate START_DATA = LocalDate.of(1895, 12, 28);

	public List<Film> getAllFilms() {
		return filmStorage.getAllFilms();
	}

	public Film getFilmById(Integer id) {
		return filmStorage.getFilmById(id);
	}

	public List<Film> getFilmsPopular(Integer count) {
		return filmStorage.getFilmsPopular(count);
	}

	public Film createFilm(Film film) {
		validateReleaseDate(film, "Добавлен");
		return filmStorage.create(film);
	}

	public Film updateFilm(Film film) {
		validateReleaseDate(film, "Обновлен");
		return filmStorage.update(film);
	}

	public void addLike(Integer filmId, Integer userId) {
		userStorage.getUserById(userId);
		filmStorage.addLike(filmId, userId);
		log.info("Как и для фильма id={} added", filmId);
	}

	public void deleteLike(Integer filmId, Integer userId) {
		userStorage.getUserById(userId);
		filmStorage.deleteLike(filmId, userId);
		log.info("Как и для фильма id={} deleted", filmId);
	}

	public void validateReleaseDate(Film film, String text) {
		if (film.getReleaseDate().isBefore(START_DATA)) {
			throw new ValidationException("Дата релиза не может быть раньше " + START_DATA);
		}
		log.debug("{} фильм: {}", text, film.getName());
	}
}
