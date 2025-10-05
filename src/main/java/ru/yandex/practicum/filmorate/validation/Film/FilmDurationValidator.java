package ru.yandex.practicum.filmorate.validation.Film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.Film;

public class FilmDurationValidator implements
        ConstraintValidator<FilmDurationConstraint, Film> {

    @Override
    public void initialize(FilmDurationConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Film film,
                           ConstraintValidatorContext cxt) {
        return film.getDuration().isNegative();
    }

}