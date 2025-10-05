package ru.yandex.practicum.filmorate.validation.User;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class BirthdayValidator implements
        ConstraintValidator<BirthdayConstraint, User> {

    @Override
    public void initialize(BirthdayConstraint releaseDate) {
    }

    @Override
    public boolean isValid(User user,
                           ConstraintValidatorContext cxt) {
        return user.getBirthday().isAfter(LocalDate.now());
    }

}
