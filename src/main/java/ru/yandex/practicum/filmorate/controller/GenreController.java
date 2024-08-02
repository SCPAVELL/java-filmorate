package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreController {
	private final GenreService genreService;

	@GetMapping
	public ResponseEntity<List<GenreDto>> getFilms() {
		return ResponseEntity.status(HttpStatus.OK).body(genreService.getGenreList().stream()
				.map(GenreMapper.mapper::mapToGenreDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	public ResponseEntity<GenreDto> getFilm(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(GenreMapper.mapper.mapToGenreDto(genreService.getGenre(id)));
	}
}