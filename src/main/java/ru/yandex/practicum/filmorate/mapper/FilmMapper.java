package ru.yandex.practicum.filmorate.mapper;

import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.model.Film;

public class FilmMapper {
	public static Film mapToFilm(FilmDto filmDto) {
		Film film = Film.builder().id(filmDto.getId()).name(filmDto.getName()).duration(filmDto.getDuration())
				.description(filmDto.getDescription()).releaseDate(filmDto.getReleaseDate()).mpa(filmDto.getMpa())
				.build();
		film.getLikes().addAll(filmDto.getLikes());
		film.getGenres().addAll(filmDto.getGenres());
		film.getDirectors().addAll(filmDto.getDirectors());
		return film;
	}

	public static FilmDto mapToFilmDto(Film film) {
		FilmDto filmDto = FilmDto.builder().id(film.getId()).name(film.getName()).duration(film.getDuration())
				.description(film.getDescription()).releaseDate(film.getReleaseDate()).mpa(film.getMpa()).build();
		filmDto.getLikes().addAll(film.getLikes());
		filmDto.getGenres().addAll(film.getGenres());
		filmDto.getDirectors().addAll(film.getDirectors());
		return filmDto;
	}
}