package ru.yandex.practicum.filmorate.controller;

import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@Valid @PathVariable("id") Integer userId) {
		return userService.getUserById(userId);
	}

	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		userService.setUserNameByLogin(user, "Обновлен");
		return userService.updateUser(user);
	}

	@PutMapping("/{id}/friends/{friendId}")
	public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
		userService.addFriend(id, friendId);
	}

	@DeleteMapping("/{id}/friends/{friendId}")
	public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
		userService.deleteFriend(id, friendId);
	}

	@GetMapping("/{id}/friends")
	public List<User> getFriendsSet(@PathVariable Integer id) {
		return userService.getUserFriends(id);
	}

	@GetMapping("/{id}/friends/common/{otherId}")
	public Set<User> getMutualFriends(@PathVariable("id") Integer id, @PathVariable("otherId") Integer otherId) {
		return userService.getMutualFriends(id, otherId);
	}
}