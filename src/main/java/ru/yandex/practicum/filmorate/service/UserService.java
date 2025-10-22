package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    // Добавление пользователя в друзья
    public void addFriend(Long userId, Long friendId) {
        userStorage.addFriend(userId, friendId);
    }

    // Вывод списка общих друзей
    public List<Long> getCommonFriends(Long userId, Long otherId) {
        return userStorage.getCrossFriendList(userId, otherId);
    }

    //вывод списка всех друзей пользователя
    public List<Long> getFriendList(Long userId) {
        return userStorage.getFriendList(userId).stream().toList();
    }

    //удаление друга
    public void removeFriend(Long userId, Long friendId) {
        userStorage.removeFriend(userId, friendId);
    }
}


