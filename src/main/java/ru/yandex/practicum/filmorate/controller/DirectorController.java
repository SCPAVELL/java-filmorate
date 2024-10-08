package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.service.DirectorService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {
	private final DirectorService directorService;

	@GetMapping("/{id}")
	public Director getDirector(@PathVariable Long id) {
		return directorService.getDirector(id);
	}

	@GetMapping
	public Collection<Director> getDirectors() {
		return directorService.getDirectors();
	}

	@PostMapping
	@Valid
	@NotNull
	public Director addDirector(@RequestBody Director director) {
		return directorService.addDirector(director);
	}

	@PutMapping
	@Valid
	@NotNull
	public Director updateDirector(@RequestBody Director director) {
		return directorService.changeDirector(director);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
		directorService.deleteDirector(id);
		return ResponseEntity.noContent().build();
	}
}