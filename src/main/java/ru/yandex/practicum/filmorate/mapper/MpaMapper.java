package ru.yandex.practicum.filmorate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.filmorate.dto.MpaDto;
import ru.yandex.practicum.filmorate.model.Mpa;

@Mapper
public interface MpaMapper {
	MpaMapper mapper = Mappers.getMapper(MpaMapper.class);

	Mpa mapToMpa(MpaDto mpaDto);

	MpaDto mapToMpaDto(Mpa mpa);
}
