package ru.yandex.practicum.filmorate.model.service;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
	private final UserStorage userStorage;

	/**
	 * Получение всех пользователей
	 */
	public List<User> getAllUsers() {
		return userStorage.getAllUsers();
	}

	/**
	 * Получение пользователя
	 */
	public User getUserById(Integer userId) {
		return userStorage.getUserById(userId);
	}

	/**
	 * Создание нового пользователя
	 */
	public User createUser(User user) {
		setUserNameByLogin(user, "Added");
		return userStorage.create(user);
	}

	/**
	 * Редактирование пользователя
	 */
	public User updateUser(User user) {
		setUserNameByLogin(user, "Updated");
		return userStorage.update(user);
	}

	/**
	 * Добавление в список друзей
	 */
	public void addFriend(Integer userId, Integer friendId) {
		try {
			User user = getUserById(userId);
			User friend = getUserById(friendId);
			user.addFriend(friendId);
			friend.addFriend(userId);
			log.debug("User with id {} added a user to the friends list with id {}", userId, friendId);
		} catch (UserNotFoundException e) {
			log.error("User с id={} not found", userId);
			throw new UserNotFoundException("User с id=" + userId + " not found");
		}

	}

	/**
	 * Удаление из списка друзей
	 */
	public void deleteFriend(Integer userId, Integer friendId) {
		User user = getUserById(userId);
		User friend = getUserById(friendId);
		user.getFriends().remove(friendId);
		friend.getFriends().remove(userId);
		log.debug("User with id {} deleted from the friends list by a user with id {}", userId, friendId);
	}

	/**
	 * Получение всех друзей пользователя
	 */
	public List<User> getUserFriends(Integer userId) {
		try {
			User user = getUserById(userId);
			return userStorage.getUserFriends(userId);
		} catch (UserNotFoundException e) {
			log.error("User with id={} not found", userId);
			throw new UserNotFoundException("User with id=" + userId + " not found");
		}
	}

	/**
	 * Получение общих друзей с другим пользователем
	 */
	public Set<User> getMutualFriends(Integer userId, Integer otherId) {
		try {
			User user = getUserById(userId);
			User otherUser = getUserById(otherId);
			return user.getFriendsId().stream().filter(otherUser.getFriendsId()::contains).map(this::getUserById)
					.collect(Collectors.toSet());
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("The user was not found.");
		}
	}

	public void setUserNameByLogin(User user, String text) {
		if (user.getName() == null || user.getName().isBlank()) {
			user.setName(user.getLogin());
		}
		log.debug("{} user: {}, email: {}", text, user.getName(), user.getEmail());
	}
}
