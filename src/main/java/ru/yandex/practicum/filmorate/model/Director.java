package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
public class Director {
	@NotNull
	@Positive
	private int id;

	@NotBlank
	private String name;

}