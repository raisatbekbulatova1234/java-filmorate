package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    //создание пользователя;
    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        if (user.getName() == null) user.setName(user.getLogin());
        user.setId(nextId());
        users.put(user.getId(), user);
        log.info("Пользователь'{}' с id - '{}' был создан", user.getName(), user.getId());
        return user;
    }


    //обновление пользователя;
    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        if (user == null) {
            throw new ValidationException("Пользователь не может быть null");
        }
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователя с таким ID не существует");
        }

        if (user.getName() == null) user.setName(user.getLogin());
        user.setId(nextId());
        users.put(user.getId(), user);
        log.info("Информация о пользователе'{}' с id - '{}' была обновлена", user.getName(), user.getId());
        return user;
    }


    //получение списка всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }


    private int nextId() {
        int currentMaxId = Math.toIntExact(users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0));
        return ++currentMaxId;
    }
}
