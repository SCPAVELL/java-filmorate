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
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.service.MpaService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/mpa")
public class MpaController {
	private final MpaService mpaService;

	@Autowired(required = false)
	public MpaController(MpaService mpaService) {
		this.mpaService = mpaService;
	}

	@GetMapping
	public Collection<Mpa> findAll() {
		log.info("Получен запрос GET к эндпоинту: /mpa");
		return mpaService.getAllMpa();
	}

	@GetMapping("/{id}")
	public Mpa findGenre(@PathVariable String id) {
		log.info("Получен запрос GET к эндпоинту: /mpa/{}", id);
		try {
			return mpaService.getMpa(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}