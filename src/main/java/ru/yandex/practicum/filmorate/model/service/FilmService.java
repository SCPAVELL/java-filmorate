package ru.yandex.practicum.filmorate.model.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.List;

@Service
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
		validateReleaseDate(film, "Added");
		return filmStorage.create(film);
	}

	public Film updateFilm(Film film) {
		validateReleaseDate(film, "Updated");
		return filmStorage.update(film);
	}

	public void addLike(Integer filmId, Integer userId) {
		Film film = filmStorage.getFilmById(filmId);
		if (film == null) {
			log.error("Film for id={} failed", filmId);
			throw new FilmNotFoundException("Film for id=" + filmId + " failed");
		}
		try {
			User user = userStorage.getUserById(userId);
			if (user == null) {
				log.error("User for id={} failed", userId);
				throw new UserNotFoundException("User for id=" + userId + " failed");
			}
			filmStorage.addLike(filmId, userId);
			log.info("like for film with id={} added", filmId);
		} catch (FilmNotFoundException e) {
			log.error("Film for id={} failed", filmId, e);
			throw new FilmNotFoundException("Film for id=" + filmId + " failed");
		} catch (UserNotFoundException e) {
			log.error("User for id={} failed", userId, e);
			throw new UserNotFoundException("User for id=" + userId + " failed");
		}
	}

	// Проверка на существование фильма по id
	public void deleteLike(Integer filmId, Integer userId) {
		Film film = filmStorage.getFilmById(filmId);
		if (film == null) {
			log.error("Film for id={} failed", filmId);
			throw new FilmNotFoundException("Film for id=" + filmId + " failed");
		}
		try {
			User user = userStorage.getUserById(userId);
			if (user == null) {
				log.error("User for id={} failed", userId);
				throw new UserNotFoundException("User for id=" + userId + " failed");
			}
			filmStorage.deleteLike(filmId, userId);
			log.info("like for film with id={} deleted", filmId);
		} catch (FilmNotFoundException e) {
			log.error("Film for id={} failed", filmId, e);
			throw new FilmNotFoundException("Film for id=" + filmId + " failed");
		} catch (UserNotFoundException e) {
			log.error("User for id={} failed", userId, e);
			throw new UserNotFoundException("User for id=" + userId + " failed");
		}
	}

	public void validateReleaseDate(Film film, String text) {
		if (film.getReleaseDate().isBefore(START_DATA)) {
			throw new ValidationException("The release date cannot be earlier " + START_DATA);
		}
		log.debug("{} Film: {}", text, film.getName());
	}
}