package ru.yandex.practicum.filmorate.storage;

import java.util.List;
import ru.yandex.practicum.filmorate.model.Film;

public interface FilmStorage {
	Film create(Film film);

	Film update(Film film);

	List<Film> getAllFilms();

	Film getFilmById(Integer filmId);

	List<Film> getFilmsPopular(Integer count);

	void addLike(Integer filmId, Integer userId);

	void deleteLike(Integer filmId, Integer userId);
}