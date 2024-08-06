package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
	private final FilmService filmService;

	@GetMapping("/{id}")
	public Film getFilm(@PathVariable("id") Long id) {
		return filmService.getFilm(id);
	}

	@GetMapping
	public Collection<Film> getFilms() {
		return filmService.getFilms();
	}

	@PostMapping
	public Film create(@RequestBody Film film) {
		return filmService.addFilm(film);
	}

	@PutMapping
	public Film update(@RequestBody Film newFilm) {
		return filmService.changeFilm(newFilm);
	}

	@PutMapping("/{id}/like/{userId}")
	public void addLike(@PathVariable("id") Long filmId, @PathVariable("userId") Long userId) {
		filmService.addLike(filmId, userId);
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void deleteLike(@PathVariable("id") Long filmId, @PathVariable("userId") Long userId) {
		filmService.deleteLike(filmId, userId);
	}

	// GET /films/popular?count={limit}&genreId={genreId}&year={year}
	@GetMapping("/popular")
	public Collection<Film> getPopular(@RequestParam(defaultValue = "10") int count,
			@RequestParam(required = false) Integer genreId, @RequestParam(required = false) Integer year) {
		if (genreId == null) {
			genreId = 0; // значение по умолчанию для genreId
		}
		if (year == null) {
			year = 0; // значение по умолчанию для year
		}
		return filmService.getPopularByYear(count, genreId, year);
	}

	@DeleteMapping({ "/{id}" })
	public void deleteFilm(@PathVariable("id") Long filmId) {
		filmService.deleteFilm(filmId);
	}

	@GetMapping("/common")
	public Collection<Film> getCommonFilms(@RequestParam Long userId, @RequestParam Long friendId) {
		return filmService.getCommonFilms(userId, friendId);
	}

	@GetMapping("/director/{directorId}")
	public Collection<Film> getSortedDirectorsFilms(@PathVariable("directorId") Long directorId,
			@RequestParam String sortBy) {
		return filmService.getSortedDirectorsFilms(directorId, sortBy);
	}

	@GetMapping("/search")
	public Collection<Film> search(@RequestParam(defaultValue = "") String query,
			@RequestParam(defaultValue = "title") String by) {
		return filmService.search(query, by);
	}
}