package ru.yandex.practicum.filmorate.model.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.Collection;

@Service
public class MpaService {
	private final MpaStorage mpaStorage;

	public MpaService(MpaStorage mpaStorage) {
		this.mpaStorage = mpaStorage;
	}

	public Collection<Mpa> getAllMpa() {
		return mpaStorage.getAllMpa();
	}

	public Mpa getMpa(String supposedId) {
		try {
			int id = intFromString(supposedId);
			Mpa mpa = mpaStorage.getMpaById(id);
			if (mpa == null) {
				throw new NotFoundException("MPA с id " + id + " не найден");
			}
			return mpa;
		} catch (NumberFormatException e) {
			throw new NotFoundException("Некорректный идентификатор: " + supposedId);
		}
	}

	private Integer intFromString(final String supposedInt) {
		try {
			return Integer.valueOf(supposedInt);
		} catch (NumberFormatException exception) {
			throw new NotFoundException("Некорректный идентификатор: " + supposedInt);
		}
	}
}
