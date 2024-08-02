package ru.yandex.practicum.filmorate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.filmorate.dto.DirectorDto;
import ru.yandex.practicum.filmorate.model.Director;

@Mapper
public interface DirectorMapper {
	DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

	Director mapToDirector(DirectorDto directorDto);

	DirectorDto mapToDirectorDto(Director director);
}
