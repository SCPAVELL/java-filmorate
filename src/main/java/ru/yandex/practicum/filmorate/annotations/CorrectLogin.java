package ru.yandex.practicum.filmorate.annotations;

import java.lang.annotation.*;
import ru.yandex.practicum.filmorate.annotations.validator.LoginValidator;
import jakarta.validation.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = LoginValidator.class)
public @interface CorrectLogin {
	String message() default "не должно быть свободного места";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
