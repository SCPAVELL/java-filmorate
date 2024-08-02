package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.enums.Search;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.service.FilmService;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilmController {
	private final FilmService filmService;

	@PostMapping
	public ResponseEntity<FilmDto> addFilm(@Valid @RequestBody FilmDto film) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(FilmMapper.mapToFilmDto(filmService.addFilm(FilmMapper.mapToFilm(film))));
	}

	@PutMapping
	public ResponseEntity<FilmDto> updateFilm(@Valid @RequestBody FilmDto film) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(FilmMapper.mapToFilmDto(filmService.updateFilm(FilmMapper.mapToFilm(film))));
	}

	@GetMapping
	public ResponseEntity<List<FilmDto>> getFilms() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(filmService.getFilms().stream().map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}

	@PutMapping("{id}/like/{userId}")
	public void addLike(@PathVariable Integer userId, @PathVariable("id") Integer filmId) {
		filmService.addLike(userId, filmId);
	}

	@GetMapping("{id}")
	public ResponseEntity<FilmDto> getFilm(@PathVariable("id") Integer filmId) {
		return ResponseEntity.status(HttpStatus.OK).body(FilmMapper.mapToFilmDto(filmService.getFilm(filmId)));
	}

	@DeleteMapping("{id}/like/{userId}")
	public void deleteLike(@PathVariable Integer userId, @PathVariable("id") Integer filmId) {
		filmService.deleteLike(userId, filmId);
	}

	@GetMapping("popular")
	public ResponseEntity<List<FilmDto>> getPopularFilms(@RequestParam(defaultValue = "10") Integer count,
			@RequestParam(required = false) Integer genreId, @RequestParam(required = false) Integer year) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.getTopFilms(count, genreId, year).stream()
				.map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}

	@GetMapping("director/{directorId}")
	public ResponseEntity<List<FilmDto>> getDirectorFilms(@PathVariable Integer directorId,
			@RequestParam(defaultValue = "likes") String sortBy) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.getSortedDirectorFilms(directorId, sortBy).stream()
				.map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}

	@DeleteMapping("{id}")
	public void deleteFilm(@PathVariable("id") Integer filmId) {
		filmService.deleteFilm(filmId);
	}

	@GetMapping("common")
	public ResponseEntity<List<FilmDto>> getCommonFilms(@RequestParam Integer userId, @RequestParam Integer friendId) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.getCommonsFilms(userId, friendId).stream()
				.map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}

	@GetMapping("/search")
	public ResponseEntity<List<FilmDto>> searchFilms(@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "by", required = false) Search by) {
		return ResponseEntity.status(HttpStatus.OK).body(
				filmService.searchFilms(query, by).stream().map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}
}
