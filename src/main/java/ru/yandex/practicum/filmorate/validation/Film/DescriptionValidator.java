package ru.yandex.practicum.filmorate.validation.Film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.Film;

public class DescriptionValidator implements
        ConstraintValidator<DescriptionConstraint, Film> {

    @Override
    public void initialize(DescriptionConstraint releaseDate) {
    }

    @Override
    public boolean isValid(Film film,
                           ConstraintValidatorContext cxt) {
        return (film.getDescription().length() > 200);
    }

}