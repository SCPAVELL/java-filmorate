package ru.yandex.practicum.filmorate.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.Collection;

@Service
public class GenreService {

	private final GenreStorage genreStorage;

	@Autowired
	public GenreService(GenreStorage genreStorage) {
		this.genreStorage = genreStorage;
	}

	public Collection<Genre> getAllGenres() {
		return genreStorage.getAllGenres();
	}

	public Collection<Genre> getFilmGenres(int filmId) {
		return genreStorage.getGenresByFilmId(filmId);
	}

	public Genre getGenre(String supposedId) {
		try {
			int genreId = intFromString(supposedId);
			Genre genre = genreStorage.getGenreById(genreId);
			if (genre == null) {
				throw new NotFoundException("Жанр с id " + genreId + " не найден");
			}
			return genre;
		} catch (NumberFormatException e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный идентификатор: " + supposedId, e);
		}
	}

	public boolean deleteFilmGenres(int filmId) {
		return genreStorage.deleteFilmGenres(filmId);
	}

	public boolean addFilmGenres(int filmId, Collection<Genre> genres) {
		return genreStorage.addFilmGenres(filmId, genres);
	}

	private Integer intFromString(final String supposedInt) {
		try {
			return Integer.valueOf(supposedInt);
		} catch (NumberFormatException exception) {
			throw new NotFoundException("Некорректный идентификатор: " + supposedInt);
		}
	}
}