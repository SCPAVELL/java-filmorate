package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	private UserController userController;

	@Test
	void shouldReturn200whenGetUsers() throws Exception {
		User user = new User();
		user.setEmail("correct.email@mail.ru");
		user.setLogin("correctlogin");
		user.setName("Correct Name");

	}

	@Test
	void shouldReturn200whenPostCorrectUserData() throws Exception {
		User user = new User();
		user.setEmail("correct.email@mail.ru");
		user.setLogin("Correct description");
		user.setName("Correct Name");
		user.setBirthday(LocalDate.of(2002, 1, 1));

	}
}