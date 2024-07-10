package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.annotations.ReleaseDateValidation;

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
	@PastOrPresent(message = "Дата релиза не раньше 28 декабря 1895 года. ")
	@ReleaseDateValidation
	private LocalDate releaseDate;
	@Positive(message = "Продолжительность фильма должна быть положительным числом. ")
	private long duration;

	private int rate;
	@NotNull
	private Mpa mpa;
	private List<Genre> genres = new ArrayList<>();
	private List<Integer> likes = new ArrayList<>();

	public boolean addLike(Integer userId) {
		return likes.add(userId);
	}

	public boolean deleteLike(Integer userId) {
		return likes.remove(userId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Film))
			return false;
		Film film = (Film) o;
		return getId() == film.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

}
