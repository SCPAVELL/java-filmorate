package ru.yandex.practicum.filmorate.annotations;

import java.lang.annotation.*;

import ru.yandex.practicum.filmorate.annotations.validator.FilmReleaseDateValidator;
import jakarta.validation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FilmReleaseDateValidator.class)
public @interface CorrectReleaseDay {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}