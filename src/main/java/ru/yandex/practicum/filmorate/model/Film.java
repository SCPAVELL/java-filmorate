package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.annotations.CorrectReleaseDay;

/**
 * Film.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

	@PositiveOrZero(message = "id не может быть отрицательным")
	private int id;
	@NotBlank(message = "Имя должно содержать буквенные символы. ")
	private String name;
	@Length(min = 1, max = 200, message = "Описание фильма не должно превышать 200 символов. ")
	private String description;
	@CorrectReleaseDay(message = "Дата релиза не раньше 28 декабря 1895 года. ")
	private LocalDate releaseDate;
	@Positive(message = "Продолжительность фильма должна быть положительным числом. ")
	private long duration;

	private Set<Integer> likes;

	public Film(String name, String description, LocalDate releaseDate, Integer duration, Set<Integer> likes) {
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.likes = Objects.requireNonNullElseGet(likes, HashSet::new);
	}

	public void addLike(Integer id) {
		if (likes == null) {
			likes = new HashSet<>();
		}
		likes.add(id);
	}

	public void deleteLike(Integer id) {
		likes.remove(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, duration, id, name, releaseDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && duration == other.duration && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(releaseDate, other.releaseDate);
	}

}
