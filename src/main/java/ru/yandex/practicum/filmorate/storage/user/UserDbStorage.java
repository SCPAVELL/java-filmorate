package ru.yandex.practicum.filmorate.storage.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;

@Component
@Qualifier("UserDbStorage")
@AllArgsConstructor
public class UserDbStorage implements UserStorage {
	private final UserRepository userRepository;

	public User getUser(Long id) {
		Optional<User> user = userRepository.getUser(id);
		if (user.isEmpty()) {
			throw new NotFoundException("Пользователь с id = " + id + " не найден");
		}
		return user.get();
	}

	@Override
	public Collection<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Collection<User> getFriends(Long id) {
		getUser(id);
		return userRepository.getFriends(id);
	}

	@Override
	public Collection<User> getMutualFriends(Long id, Long otherId) {
		getUser(id);
		getUser(otherId);

		return userRepository.getMutualFriends(id, otherId);
	}

	@Override
	public User addUser(User user) {
		if (user.getName() == null || user.getName().isBlank())
			user.setName(user.getLogin());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		getUser(id);
		userRepository.delete(id);
	}

	@Override
	public User changeUser(User newUser) {
		// проверяем необходимые условия
		if (newUser.getId() == null) {
			throw new ValidationException("Id должен быть указан");
		}
		getUser(newUser.getId());
		return userRepository.update(newUser);
	}

	@Override
	public void addFriend(Long userId, Long friendId) {
		getUser(userId);
		getUser(friendId);
		if (userRepository.existFriend(friendId, userId)) {
			userRepository.addFriend(userId, friendId, true);
			userRepository.updateFriend(friendId, userId, true);
		} else {
			userRepository.addFriend(userId, friendId, false);
		}
	}

	@Override
	public void deleteFriend(Long userId, Long friendId) {
		getUser(userId);
		getUser(friendId);
		userRepository.deleteFriend(userId, friendId);
	}
}