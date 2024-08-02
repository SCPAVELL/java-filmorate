package ru.yandex.practicum.filmorate.service.likers;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface LikerService {
	void add(Long filmId, Long userId);

	void delete(Long filmId, Long userId);

	List<User> getLikersByFilmId(Long id);

	List<Film> getFilmsByLikerId(Long id);

	List<Film> getMostPopularFilms(Long count);
}
