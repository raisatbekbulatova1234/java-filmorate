package ru.yandex.practicum.filmorate.validation.Film;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmDurationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmDurationConstraint {
    String message() default "Продолжительность фильма должна быть положительным числом";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}