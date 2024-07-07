package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.service.UserService;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(@Autowired(required = false) UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public Collection<User> findAll() {
		log.info("Получен запрос GET к эндпоинту: /users");
		return userService.getAllUsers();
	}

	@GetMapping("/{id}/friends")
	public Collection<User> findFriends(@PathVariable String id) {
		log.info("Получен запрос GET к эндпоинту: /users/{}/friends", id);
		return userService.getFriends(id);
	}

	@GetMapping("/{id}")
	public User findUser(@PathVariable String id) {
		log.info("Получен запрос GET к эндпоинту: /users/{}/", id);
		return userService.getUser(id);
	}

	@GetMapping("/{id}/friends/common/{otherId}")
	public Collection<User> findCommonFriends(@PathVariable String id, @PathVariable String otherId) {
		log.info("Получен запрос GET к эндпоинту: /users/{}/friends/common/{}", id, otherId);
		return userService.getCommonFriends(id, otherId);
	}

	@PostMapping
	public User create(@RequestBody User user) {
		log.info("Получен запрос POST. Данные тела запроса: {}", user);
		final User validUser = userService.add(user);
		log.info("Создан объект {} с идентификатором {}", User.class.getSimpleName(), validUser.getId());
		return validUser;
	}

	@PutMapping
	public User put(@RequestBody User user) {
		log.info("Получен запрос PUT. Данные тела запроса: {}", user);
		try {
			final User validUser = userService.update(user);
			log.info("Обновлен объект {} с идентификатором {}", User.class.getSimpleName(), validUser.getId());
			return validUser;
		} catch (ResponseStatusException e) {
			log.error("Ошибка при обновлении пользователя: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Неизвестная ошибка при обновлении пользователя: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Неизвестная ошибка при обновлении пользователя", e);
		}
	}

	@PutMapping("/{id}/friends/{friendId}")
	public void addFriend(@PathVariable String id, @PathVariable String friendId) {
		log.info("Получен запрос PUT к эндпоинту: /users/{}/friends/{}", id, friendId);
		try {
			userService.addFriend(id, friendId);
			log.info("Обновлен объект {} с идентификатором {}. Добавлен друг {}", User.class.getSimpleName(), id,
					friendId);
		} catch (NotFoundException e) {
			log.error("Ошибка при добавлении друга: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		} catch (Exception e) {
			log.error("Неизвестная ошибка при добавлении друзей: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Неизвестная ошибка при добавлении друзей", e);
		}
	}

	@DeleteMapping("/{id}/friends/{friendId}")
	public void deleteFriend(@PathVariable String id, @PathVariable String friendId) {
		log.info("Получен запрос DELETE к эндпоинту: /users/{}/friends/{}", id, friendId);
		userService.deleteFriend(id, friendId);
		log.info("Обновлен объект {} с идентификатором {}. Удален друг {}", User.class.getSimpleName(), id, friendId);
	}
}