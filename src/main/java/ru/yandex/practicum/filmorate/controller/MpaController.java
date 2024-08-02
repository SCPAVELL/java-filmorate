package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.MpaDto;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.service.MpaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MpaController {
	private final MpaService mpaService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<MpaDto>> getMpasList() {
		return ResponseEntity.status(HttpStatus.OK).body(
				mpaService.getMpasList().stream().map(MpaMapper.mapper::mapToMpaDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	public ResponseEntity<MpaDto> getMpa(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(MpaMapper.mapper.mapToMpaDto(mpaService.getMpa(id)));
	}
}