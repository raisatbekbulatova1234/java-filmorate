package ru.yandex.practicum.filmorate.validation.Film;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DescriptionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DescriptionConstraint {
    String message() default "Максимальная длина описания — 200 символов";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}