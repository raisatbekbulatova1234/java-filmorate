package ru.yandex.practicum.filmorate.validation.User;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.User;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, String> {

    @Override
    public void initialize(LoginConstraint releaseDate) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        if (login == null || login.contains(" ")) {
            context.buildConstraintViolationWithTemplate("Логин не должен содержать пробелы")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}