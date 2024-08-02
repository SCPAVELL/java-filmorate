package ru.yandex.practicum.filmorate.dto;

import lombok.*;

import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@Builder
public class FilmDto {
	private Integer id;
	@NotBlank(message = "Название не может быть пустым")
	private String name;
	@Size(max = 200, message = "Максимальная длина описания 200 символов")
	private String description;
	private LocalDate releaseDate;
	@Positive(message = "Некорректная продолжительность фильма")
	private Integer duration;
	@EqualsAndHashCode.Exclude
	private final Set<User> likes = new HashSet<>();
	@EqualsAndHashCode.Exclude
	private final Set<Genre> genres = new TreeSet<>();
	@EqualsAndHashCode.Exclude
	private final Set<Director> directors = new HashSet<>();
	@NotNull
	@EqualsAndHashCode.Exclude
	private Mpa mpa;
}
