package ru.yandex.practicum.filmorate.service.directors;

import java.util.List;

import ru.yandex.practicum.filmorate.model.Director;

public interface DirectorService {
	List<Director> getAll();

	Director getById(int id);

	Director add(Director director);

	Director update(Director director);

	void delete(int id);

	List<Director> getByIds(List<Integer> ids);
}
