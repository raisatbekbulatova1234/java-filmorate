package ru.yandex.practicum.filmorate.validation.User;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.User;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, User> {

    @Override
    public void initialize(LoginConstraint releaseDate) {
    }

    @Override
    public boolean isValid(User user,
                           ConstraintValidatorContext cxt) {
        return user.getLogin().contains(" ");
    }

}