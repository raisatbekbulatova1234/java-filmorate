package ru.yandex.practicum.filmorate.validation.Film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

public class ReleaseDateValidator implements
        ConstraintValidator<ReleaseDateConstraint, Film> {

    @Override
    public void initialize(ReleaseDateConstraint releaseDate) {
    }

    @Override
    public boolean isValid(Film film,
                           ConstraintValidatorContext cxt) {
        return film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28));
    }

}

