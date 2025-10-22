package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.InternalServerError;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserStorage userStorage;

    //создание пользователя;
    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return userStorage.createUser(user);
    }

    //обновление пользователя;
    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        return userStorage.updateUser(user);
    }

    //получение списка всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    //получение списка всех друзей пользователя
    @GetMapping("{id}/friends")
    public List<Long> getAllFriends(@PathVariable("id") Long userId) {
        return userService.getFriendList(userId);
    }

    //получение списка общих друзей пользователей
    @GetMapping("{id}/friends/common/{otherId}")
    public List<Long> getAllCommonFriends(@PathVariable("id") Long userId,
                                          @PathVariable("otherId") Long otherId) {
        return userService.getCommonFriends(userId, otherId);
    }

    //добавление друга
    @PutMapping("{userId}/friends/{friendId}")
    public ResponseEntity<String> addFriend(@PathVariable("userId") Long userId,
                                            @PathVariable("friendId") Long friendId) {
        try {
            userService.addFriend(userId, friendId);
            return ResponseEntity.ok("Пользователь успешно добавлен в друзья.");
        } catch (InternalServerError e) {
           // e.printStackTrace();
            return ResponseEntity.status(500).body("Произошла ошибка при добавлении пользователя в друзья.");
        }
    }

    //удаление друга
    @DeleteMapping("{id}/friends/{friendId}")
    public void removeFriend(@PathVariable("id") Long userId,
                             @PathVariable("friendId") Long friendId) {
        userService.removeFriend(userId, friendId);
    }
}
