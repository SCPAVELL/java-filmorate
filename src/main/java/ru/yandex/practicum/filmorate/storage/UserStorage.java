package ru.yandex.practicum.filmorate.storage;

import java.util.List;
import ru.yandex.practicum.filmorate.model.User;

public interface UserStorage {
	User create(User user);

	User update(User user);

	List<User> getAllUsers();

	User getUserById(Integer id);

	List<User> getUserFriends(Integer userId);
}