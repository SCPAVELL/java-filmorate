package ru.yandex.practicum.filmorate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DirectorDto {
	private Integer id;
	@NotBlank(message = "Имя не может быть пустым или null")
	private String name;
}
