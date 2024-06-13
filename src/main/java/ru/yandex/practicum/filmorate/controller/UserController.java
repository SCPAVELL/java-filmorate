package ru.yandex.practicum.filmorate.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.service.UserService;

@RestController
@Slf4j
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public List<User> getFilms() {
		List<User> usersList = new ArrayList<>(userService.getAllUsers().values());
		log.debug("Количество пользователей: {}", usersList.size());
		return usersList;
	}

	@PostMapping
	public User createFilm(@Valid @RequestBody User user) {
		if (userService.getAllUsers().containsKey(user.getId())) {
			throw new RuntimeException("Пользователь уже есть в базе");
		}
		userService.setUserNameByLogin(user, "Добавлен");
		return userService.createUser(user);
	}

	@PutMapping
	public User updateFilm(@RequestBody User user) {
		if (!userService.getAllUsers().containsKey(user.getId())) {
			log.error("User not found in the database");
			throw new RuntimeException("Пользователя нет в базе");
		}
		userService.setUserNameByLogin(user, "Обновлен");
		log.info("User updated: {}", user);
		return userService.updateUser(user);
	}
}
