package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.service.FilmService;
import java.util.Collection;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

	private final FilmService filmService;

	@Autowired(required = false)
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping
	public Collection<Film> findAll() {
		log.info("Получен запрос GET к эндпоинту: /films");
		return filmService.getFilms();
	}

	@GetMapping("/{id}")
	public Film findFilm(@PathVariable String id) {
		log.info("Получен запрос GET к эндпоинту: /films/{}", id);
		return filmService.getFilm(id);
	}

	@GetMapping({ "/popular?count={count}", "/popular" })
	public Collection<Film> findMostPopular(@RequestParam(defaultValue = "10") String count) {
		log.info("Получен запрос GET к эндпоинту: /films/popular?count={}", count);
		return filmService.getMostPopularFilms(count);
	}

	@PostMapping
	public Film create(@RequestBody Film film) {
		log.info("Получен запрос POST. Данные тела запроса: {}", film);
		try {
			Film validFilm = filmService.add(film);
			log.info("Создан объект {} с идентификатором {}", Film.class.getSimpleName(), validFilm.getId());
			return validFilm;
		} catch (DataIntegrityViolationException e) {
			log.error("Ошибка при создании фильма: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (Exception e) {
			log.error("Непредвиденная ошибка при создании фильма: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Непредвиденная ошибка при создании фильма", e);
		}
	}

	@PutMapping
	public Film put(@RequestBody Film film) {
		log.info("Получен запрос PUT. Данные тела запроса: {}", film);
		try {
			Film validFilm = filmService.update(film);
			log.info("Обновлен объект {} с идентификатором {}", Film.class.getSimpleName(), validFilm.getId());
			return validFilm;
		} catch (DataIntegrityViolationException e) {
			log.error("Ошибка при обновлении фильма: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (Exception e) {
			log.error("Непредвиденная ошибка при обновлении фильма: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Непредвиденная ошибка при обновлении фильма", e);
		}
	}

	@PutMapping("/{id}/like/{userId}")
	public void putLike(@PathVariable String id, @PathVariable String userId) {
		log.info("Получен запрос PUT к эндпоинту: /films/{}/like/{}", id, userId);
		filmService.addLike(id, userId);
		log.info("Обновлен объект {} с идентификатором {}, добавлен лайк от пользователя {}",
				Film.class.getSimpleName(), id, userId);
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void deleteLike(@PathVariable String id, @PathVariable String userId) {
		log.info("Получен запрос DELETE к эндпоинту: films/{}/like/{}", id, userId);
		filmService.deleteLike(id, userId);
		log.info("Обновлен объект {} с идентификатором {}, удален лайк от пользователя {}", Film.class.getSimpleName(),
				id, userId);

	}
}