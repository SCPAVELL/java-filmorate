package ru.yandex.practicum.filmorate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.model.Genre;

@Mapper
public interface GenreMapper {
	GenreMapper mapper = Mappers.getMapper(GenreMapper.class);

	Genre mapToGenre(GenreDto genreDto);

	GenreDto mapToGenreDto(Genre genre);
}