package ru.yandex.practicum.filmorate.validation.User;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthdayConstraint {
    String message() default "Дата рождения не может быть в будущем";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}