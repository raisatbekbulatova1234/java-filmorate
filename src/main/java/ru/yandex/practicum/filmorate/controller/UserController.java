package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("/users")
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    //создание пользователя;
    public void createUser(User user) {

        if (user != null) {
            if (user.getEmail() == null || !user.getEmail().contains("@")) {
                throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
            }
            if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
                throw new ValidationException("Логин не может быть пустым и содержать пробелы");
            }
            LocalDate birthday = LocalDate.parse(user.getBirthday());
            Instant birthdayDate = birthday.atStartOfDay(ZoneId.systemDefault()).toInstant();
            if (birthdayDate.isAfter(Instant.now())) {
                throw new ValidationException("Дата рождения не может быть в будущем");
            }
            if (users.containsKey(user.getId())) {
                throw new ValidationException("Пользователь уже существует");
            }
            if (user.getName() == null) user.setName(user.getLogin());
            users.put(user.getId(), user);
        }
    }

    //обновление пользователя;
    public void updateUser(User user) {
        if (user == null) {
            throw new ValidationException("Пользователь не может быть null");
        }
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователя с таким ID не существует");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Имя пользователя не может быть пустым");
        }
        if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new ValidationException("Email пользователя некорректен");
        }

        users.put(user.getId(), user);
    }

    //получение списка всех пользователей
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
