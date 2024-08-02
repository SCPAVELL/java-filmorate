package ru.yandex.practicum.filmorate.model.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ReleaseDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReleaseDate {
	String message() default "Дата релиза должна быть не раньше 28 декабря 1895 года";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
