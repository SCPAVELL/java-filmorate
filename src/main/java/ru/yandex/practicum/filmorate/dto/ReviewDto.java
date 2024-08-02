package ru.yandex.practicum.filmorate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
	private int reviewId;
	@NotBlank
	private String content;
	@NotNull
	@JsonProperty("isPositive")
	private Boolean isPositive;
	@NotNull
	private Integer userId;
	@NotNull
	private Integer filmId;
	private Integer useful;
}