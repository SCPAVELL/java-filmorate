package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerTest {

	@MockBean
	private FilmController filmController;

	@Test
	void shouldReturn200whenGetFilms() throws Exception {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);

	}

	@Test
	void shouldReturn200whenPostCorrectFilmData() throws Exception {
		Film film = new Film();
		film.setName("Correct Name");
		film.setDescription("Correct description");
		film.setReleaseDate(LocalDate.of(1995, 5, 26));
		film.setDuration(100L);

	}
}