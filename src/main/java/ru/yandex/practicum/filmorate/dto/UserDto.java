package ru.yandex.practicum.filmorate.dto;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Data
@Builder
public class UserDto {
	private Integer id;
	@Email(message = "некорректный email")
	private String email;
	@NotBlank(message = "Логин не может быть пустым или null")
	private String login;
	private String name;
	@Past(message = "Дата рождения не может быть в будущем")
	private LocalDate birthday;
	private final Set<User> friends = new HashSet<>();
}
