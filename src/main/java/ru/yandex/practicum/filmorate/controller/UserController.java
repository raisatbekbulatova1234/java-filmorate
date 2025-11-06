package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@Slf4j
@RestController
@Component
public class UserController {
    @Qualifier("UserDBService")
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    static final String PATH_FOR_USERS = "/users";
    private static final String PATH_FOR_ID_VARIABLE = "/{id}";
    private static final String PATH_FOR_FRIENDS = "/friends";
    private static final String PATH_FOR_FRIENDS_ID_VARIABLE = "/{friendId}";
    private static final String PATH_FOR_COMMON = "/common";
    private static final String PATH_FOR_OTHER_ID_VARIABLE = "/{otherId}";

    /**
     * Получение списка всех пользователей.
     */

    @GetMapping("/users")
    public List<User> getAllUsers() {
        log.info("Выдан ответ на запрос всех пользователей.");
        return userService.getAllUsers();
    }

    /**
     * Создание пользователей.
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        User createdUser = userService.addToStorage(user);
        log.info("Выдан ответ на запрос создания пользователя: {}", createdUser);
        return ResponseEntity.ok(createdUser);
    }


    /**
     * Обновление информации о существующем пользователе.
     */
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateInStorage(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Получение пользователя по ID.
     */
    @GetMapping("/users" + "/{id}")
    public User getUser(@PathVariable Integer id) {
        log.info("Выдан ответ на запрос пользователя по ID = " + id + ".");
        return userService.getUserById(id);
    }

    /**
     * Удаление пользователя из БД.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer userId) {
        userService.removeFromStorage(userId);
        String message = "Выполнено удаление пользователя  из БД с ID = '" + userId + ".";
        log.info(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


    /**
     * PUT /users/{id}/friends/{friendId} — добавление в друзья.
     */
    @PutMapping("/users" + "/{id}" + "/friends" + "/{friendId}")
    public ResponseEntity<?> addEachOtherAsFriends(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addEachOtherAsFriends(id, friendId);
        log.info("Пользователь (ID = " + id + ") подружился с пользователем (ID = " + friendId + ").");
        return ResponseEntity.status(HttpStatus.OK).body("Запрос на дружбу с пользователем (ID = "
                + friendId + ") успешно обработан.");
    }

    /**
     * DELETE /users/{id}/friends/{friendId} — удаление из друзей.
     */
    @DeleteMapping("/users" + "/{id}" + "/friends" + "/{friendId}")
    public ResponseEntity<?> deleteFromFriends(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.deleteFromFriends(id, friendId);
        log.info("Грусть. Дружба пользователя (ID = " + id + ") с пользователем (ID = " + friendId + ") завершена )-;");
        return ResponseEntity.status(HttpStatus.OK).body("Запрос на завершение дружбы с пользователем (ID = "
                + friendId + ") успешно обработан.");
    }

    /**
     * GET /users/{id}/friends — возвращаем список пользователей, являющихся его друзьями.
     */
    @GetMapping("/users" + "/{id}" + "/friends")
    public List<User> getUserFriends(@PathVariable Integer id) {
        List<User> result = userService.getUserFriends(id);
        log.info("Выдан ответ на запрос информации о друзьях пользователя с ID = " + id);
        return result;
    }

    /**
     * GET /users/{id}/friends/common/{otherId} — список друзей, общих с другим пользователем.
     */
    @GetMapping("/users" + "/{id}" + "/friends" + "/common" + "/{otherId}")
    public List<User> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        List<User> result = userService.getCommonFriends(id, otherId);
        log.info("Выдан ответ на запрос информации об общих друзьях пользователя с ID = " + otherId);
        return result;
    }
}