package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Feed {
	@NotNull
	private int userId;

	private String eventType;

	private String operation;

	@NotNull
	private int eventId;

	@NotNull
	private int entityId;

	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date timestamp;

}