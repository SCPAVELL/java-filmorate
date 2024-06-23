package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.service.FilmService;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmServiceTest {
	@Autowired
	private FilmService service;

	@Test
	void shouldAddWhenAddValidFilmData() {

		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description.");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);
	}

	@Test
	void shouldThrowExceptionWhenAddFailedFilmNameEmpty() {
		Film film = new Film();
		film.setName("");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1895, 12, 28));
		film.setDuration(100L);
	}

	@Test
	void shouldThrowExceptionWhenAddFailedFilmNameBlank() {
		Film film = new Film();
		film.setName("  ");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1895, 12, 28));
		film.setDuration(100L);
	}

	@Test
	void shouldThrowExceptionWhenAddFailedFilmDuration() {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(-100L);
	}

	@Test
	void shouldThrowExceptionWhenAddFailedFilmReleaseDate() {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1895, 12, 27));
		film.setDuration(100L);
	}

	@Test
	void shouldAddWhenAddValidFilmReleaseDateBoundary() {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description.");
		film.setReleaseDate(LocalDate.of(1895, 12, 28));
		film.setDuration(100L);

	}

	@Test
	void shouldThrowExceptionWhenAddFailedFilmDescription() {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Failed description. Failed description. Failed description. Failed description. "
				+ "Failed description. Failed description. Failed description. Failed description. "
				+ "Failed description. Failed description. F");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);

	}

	@Test
	void shouldAddWhenAddFilmDescriptionBoundary() {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description. Correct description. Correct description. Correct description. "
				+ "Correct description. Correct description. Correct description. Correct description. "
				+ "Correct description. Correct des");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);

	}

	@Test
	void shouldThrowExceptionWhenUpdateFailedFilmId() {
		Film film = new Film();
		film.setId(999);
		film.setName("Correct Name");
		film.setDescription("Correct description.");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);

	}
}