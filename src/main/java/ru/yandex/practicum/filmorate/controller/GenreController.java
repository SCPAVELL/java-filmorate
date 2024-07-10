package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.service.GenreService;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/genres")
public class GenreController {
	private final GenreService genreService;

	@Autowired(required = false)
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping
	public Collection<Genre> findAll() {
		log.info("Получен запрос GET к эндпоинту: /genres");
		return genreService.getAllGenres();
	}

	@GetMapping("/{id}")
	public Genre findGenre(@PathVariable String id) {
		log.info("Получен запрос GET к эндпоинту: /genres/{}", id);
		try {
			return genreService.getGenre(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}