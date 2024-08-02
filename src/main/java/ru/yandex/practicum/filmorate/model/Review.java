package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class Review {

	@Positive
	private Long reviewId;
	@NotNull
	private Long userId;
	@NotNull
	private Long filmId;
	@NotBlank
	private String content;
	@NotNull
	private Boolean isPositive;
	private int useful;
}