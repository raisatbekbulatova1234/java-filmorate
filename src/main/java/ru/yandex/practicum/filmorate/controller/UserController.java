package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    //создание пользователя;
    @ResponseBody
    @PostMapping
    public void createUser(@RequestBody User user) {
        if (user != null) {
            if (users.containsKey(user.getId())) {
                throw new ValidationException("Пользователь уже существует");
            }
            userValidation(user);
            if (user.getName() == null) user.setName(user.getLogin());
            users.put(user.getId(), user);
            log.info("Пользователь'{}' с id - '{}' был создан", user.getName(), user.getId());

        }
    }

    //обновление пользователя;
    @ResponseBody
    @PutMapping
    public void updateUser(@RequestBody User user) {
        if (user == null) {
            throw new ValidationException("Пользователь не может быть null");
        }
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователя с таким ID не существует");
        }
        userValidation(user);
        if (user.getName() == null) user.setName(user.getLogin());
        users.put(user.getId(), user);
        log.info("Информация о пользователе'{}' с id - '{}' была обновлена", user.getName(), user.getId());
    }

    //получение списка всех пользователей
    @ResponseBody
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    private void userValidation(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
        }
        if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть в будущем");
        }
        if (user.getId() == 0 || user.getId() < 0) {
            user.setId(++id);
            log.info("Некорректный id пользователя изменен на - '{}'", user.getId());
        } else id++;
    }
}
