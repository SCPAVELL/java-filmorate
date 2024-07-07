package ru.yandex.practicum.filmorate.storage;

import java.util.Collection;

import ru.yandex.practicum.filmorate.model.Genre;

public interface GenreStorage {
	Collection<Genre> getAllGenres();

	Collection<Genre> getGenresByFilmId(int filmId);

	Genre getGenreById(int genreId);

	boolean addFilmGenres(int filmId, Collection<Genre> genres);

	boolean deleteFilmGenres(int filmId);
}
