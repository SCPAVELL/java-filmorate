package ru.yandex.practicum.filmorate.storage;

import java.util.Collection;

import ru.yandex.practicum.filmorate.model.Mpa;

public interface MpaStorage {
	Collection<Mpa> getAllMpa();

	Mpa getMpaById(int mpaId);
}
