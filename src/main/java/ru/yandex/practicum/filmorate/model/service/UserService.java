package ru.yandex.practicum.filmorate.model.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

@Component
@Slf4j
@AllArgsConstructor
public class UserService {
	private final UserStorage userStorage;

	public Map<Integer, User> getAllUsers() {
		return userStorage.getUsers();
	}

	public User createUser(User user) {
		return userStorage.create(user);
	}

	public User updateUser(User user) {
		return userStorage.update(user);
	}

	public void setUserNameByLogin(User user, String text) {
		if (user.getName() == null || user.getName().isBlank()) {
			user.setName(user.getLogin());
		}
		log.debug("{} пользователь: {}, email: {}", text, user.getName(), user.getEmail());
	}
}
