package ru.yandex.practicum.filmorate.validation.Film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

public class ReleaseDateValidator implements
        ConstraintValidator<ReleaseDateConstraint, LocalDate> {

    @Override
    public void initialize(ReleaseDateConstraint releaseDate) {
    }

    @Override
    public boolean isValid(LocalDate date,
                           ConstraintValidatorContext cxt) {
        return date.isAfter(LocalDate.of(1895, 12, 28));
    }

}

