package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class User {

	private int id;
	@Email(message = "Электронная почта не может быть пустой и должна содержать символ @.")
	private String email;
	@NotBlank
	@Pattern(regexp = "^\\S*$", message = "Логин не может содержать пробелы.")
	private String login;
	private String name;
	@PastOrPresent(message = "Дата рождения не может быть в будущем.")
	private LocalDate birthday;

	private Set<Integer> friends;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(login, other.login) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, id, login, name);
	}

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
