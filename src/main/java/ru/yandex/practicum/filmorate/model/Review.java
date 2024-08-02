package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

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

	public Map<String, Object> toMap() {
		Map<String, Object> values = new HashMap<>();
		values.put("content", content);
		values.put("is_positive", isPositive);
		values.put("person_id", userId);
		values.put("film_id", filmId);
		return values;
	}
}