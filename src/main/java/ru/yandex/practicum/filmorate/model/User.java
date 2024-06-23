package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotations.CorrectLogin;

@Data
@Valid
public class User {

	@PositiveOrZero(message = "id не может быть отрицательным")
	private int id;

	@NotNull(message = "Электронная почта не может быть пустой")
	@Email(message = "Должен быть символ @.")
	private String email;

	@NotBlank(message = "login не может быть пустым")
	@CorrectLogin
	private String login;

	private String name;
	@PastOrPresent(message = "Дата рождения не может быть в будущем.")
	private LocalDate birthday;

	private Set<Integer> friends;

	public void addFriend(Integer id) {
		if (friends == null) {
			friends = new HashSet<>();
		}
		friends.add(id);
	}

	public Set<Integer> getFriendsId() {
		if (friends == null) {
			friends = new HashSet<>();
		}
		return friends;
	}

}
