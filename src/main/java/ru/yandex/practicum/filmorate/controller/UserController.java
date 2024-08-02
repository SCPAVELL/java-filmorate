package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.dto.FeedDto;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.mapper.FeedMapper;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.service.UserService;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(UserMapper.mapToUserDto(userService.addUser(UserMapper.mapToUser(user))));
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(UserMapper.mapToUserDto(userService.updateUser(UserMapper.mapToUser(user))));
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getUsers().stream().map(UserMapper::mapToUserDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer userId) {
		return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapToUserDto(userService.getUser(userId)));
	}

	@PutMapping("{id}/friends/{friendId}")
	public void addFriend(@PathVariable("id") Integer userId, @PathVariable Integer friendId) {
		userService.addFriend(userId, friendId);
	}

	@DeleteMapping("{id}/friends/{friendId}")
	public void deleteFriend(@PathVariable("id") Integer userId, @PathVariable Integer friendId) {
		userService.deleteFriend(userId, friendId);
	}

	@GetMapping("{id}/friends")
	public ResponseEntity<List<UserDto>> getUserFriends(@PathVariable("id") Integer userId) {
		return ResponseEntity.status(HttpStatus.OK).body(
				userService.getFriends(userId).stream().map(UserMapper::mapToUserDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}/friends/common/{friendId}")
	public ResponseEntity<List<UserDto>> getCommonFriends(@PathVariable("id") Integer userId,
			@PathVariable Integer friendId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getCommonFriends(userId, friendId).stream()
				.map(UserMapper::mapToUserDto).collect(Collectors.toList()));
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") Integer userId) {
		userService.delete(userId);
	}

	@GetMapping("{id}/recommendations")
	public ResponseEntity<List<FilmDto>> getRecommendation(@PathVariable("id") Integer userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getRecommendations(userId).stream()
				.map(FilmMapper::mapToFilmDto).collect(Collectors.toList()));
	}

	@GetMapping("{id}/feed")
	public ResponseEntity<List<FeedDto>> getUserFeed(@PathVariable("id") Integer userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserFeed(userId).stream()
				.map(FeedMapper.mapper::mapToFeedDto).collect(Collectors.toList()));
	}

}