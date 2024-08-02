package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class Director {
	private Integer id;
	@NotBlank(message = "Имя не может быть пустым или null")
	private String name;

	public Map<String, Object> toMap() {
		Map<String, Object> values = new HashMap<>();
		values.put("director_name", name);
		return values;
	}
}