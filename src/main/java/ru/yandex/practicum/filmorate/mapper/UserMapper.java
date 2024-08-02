package ru.yandex.practicum.filmorate.mapper;

import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.model.User;

public class UserMapper {
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
				.login(user.getLogin()).birthday(user.getBirthday()).build();
		userDto.getFriends().addAll(user.getFriends());
		return userDto;
	}

	public static User mapToUser(UserDto userDto) {
		User user = User.builder().id(userDto.getId()).name(userDto.getName()).email(userDto.getEmail())
				.login(userDto.getLogin()).birthday(userDto.getBirthday()).build();
		user.getFriends().addAll(userDto.getFriends());
		return user;
	}
}
