package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.dto.ReviewDto;
import ru.yandex.practicum.filmorate.mapper.ReviewMapper;
import ru.yandex.practicum.filmorate.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewDto> addReview(@Valid @RequestBody ReviewDto review) {
		ReviewDto addedReview = ReviewMapper.mapper
				.mapToReviewDto(reviewService.addReview(ReviewMapper.mapper.mapToReview(review)));
		log.info("Отзыв с id = {} добавлен", addedReview.getReviewId());
		return ResponseEntity.status(HttpStatus.OK).body(addedReview);
	}

	@PutMapping
	public ResponseEntity<ReviewDto> updateReview(@Valid @RequestBody ReviewDto review) {
		ReviewDto updatedReview = ReviewMapper.mapper
				.mapToReviewDto(reviewService.updateReview(ReviewMapper.mapper.mapToReview(review)));
		log.info("Отзыв с id = {} обновлен", updatedReview.getReviewId());
		return ResponseEntity.status(HttpStatus.OK).body(updatedReview);
	}

	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable("id") Integer reviewId) {
		reviewService.deleteReview(reviewId);
		log.info("Отзыв с id = {} удален", reviewId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReviewDto> getReviewById(@PathVariable("id") Integer reviewId) {
		ReviewDto review = ReviewMapper.mapper.mapToReviewDto(reviewService.getReviewById(reviewId));
		log.info("Отзыв с id = {} найден", reviewId);
		return ResponseEntity.status(HttpStatus.OK).body(review);
	}

	@GetMapping
	public ResponseEntity<List<ReviewDto>> getReviewsForFilm(@RequestParam(required = false) Integer filmId,
			@RequestParam(defaultValue = "10") Integer count) {

		List<ReviewDto> reviews = reviewService.getReviewsForFilm(filmId, count).stream()
				.map(ReviewMapper.mapper::mapToReviewDto).collect(Collectors.toList());
		if (!reviews.isEmpty()) {
			log.info("Количество отзывов {}, id самого полезного отзыва {}", reviews.size(),
					reviews.get(0).getReviewId());
		}
		return ResponseEntity.status(HttpStatus.OK).body(reviews);
	}

	@PutMapping("/{id}/like/{userId}")
	public void addLike(@PathVariable("id") Integer reviewId, @PathVariable Integer userId) {
		reviewService.addLike(reviewId, userId, true);
		log.info("добавлен лайк");
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void deleteLike(@PathVariable("id") Integer reviewId, @PathVariable Integer userId) {
		reviewService.deleteLike(reviewId, userId);
		log.info("удален лайк");
	}

	@PutMapping("/{id}/dislike/{userId}")
	public void addDislike(@PathVariable("id") Integer reviewId, @PathVariable Integer userId) {
		reviewService.addLike(reviewId, userId, false);
		log.info("добавлен дизлайк");
	}

	@DeleteMapping("/{id}/dislike/{userId}")
	public void deleteDislike(@PathVariable("id") Integer reviewId, @PathVariable Integer userId) {
		reviewService.deleteLike(reviewId, userId);
		log.info("удален дизлайк");
	}
}