package ru.yandex.practicum.filmorate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.filmorate.dto.ReviewDto;
import ru.yandex.practicum.filmorate.model.Review;

@Mapper
public interface ReviewMapper {
	ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);

	Review mapToReview(ReviewDto reviewDto);

	ReviewDto mapToReviewDto(Review review);
}
