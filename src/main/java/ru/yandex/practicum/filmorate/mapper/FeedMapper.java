package ru.yandex.practicum.filmorate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.filmorate.dto.FeedDto;
import ru.yandex.practicum.filmorate.model.Feed;

@Mapper
public interface FeedMapper {
	FeedMapper mapper = Mappers.getMapper(FeedMapper.class);

	Feed mapToFeed(FeedDto feedDto);

	FeedDto mapToFeedDto(Feed feed);
}
