package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.dto.DirectorDto;
import ru.yandex.practicum.filmorate.mapper.DirectorMapper;
import ru.yandex.practicum.filmorate.service.DirectorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DirectorController {
	private final DirectorService directorService;

	@GetMapping
	public ResponseEntity<List<DirectorDto>> getDirectors() {
		return ResponseEntity.status(HttpStatus.OK).body(directorService.getDirectorList().stream()
				.map(DirectorMapper.mapper::mapToDirectorDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	public ResponseEntity<DirectorDto> getDirector(@PathVariable("id") Integer directorId) {
		return ResponseEntity.ok(DirectorMapper.mapper.mapToDirectorDto(directorService.getDirector(directorId)));
	}

	@PostMapping
	public ResponseEntity<DirectorDto> addDirector(@Valid @RequestBody DirectorDto director) {
		return ResponseEntity.status(HttpStatus.CREATED).body(DirectorMapper.mapper
				.mapToDirectorDto(directorService.addDirector(DirectorMapper.mapper.mapToDirector(director))));
	}

	@PutMapping
	public ResponseEntity<DirectorDto> updateDirector(@Valid @RequestBody DirectorDto director) {
		return ResponseEntity.status(HttpStatus.OK).body(DirectorMapper.mapper
				.mapToDirectorDto(directorService.updateDirector(DirectorMapper.mapper.mapToDirector(director))));
	}

	@DeleteMapping("{id}")
	public void deleteDirector(@PathVariable("id") Integer directorId) {
		directorService.delete(directorId);
	}
}
