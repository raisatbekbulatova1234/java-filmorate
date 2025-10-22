package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.messages.LogMessages;
import ru.yandex.practicum.filmorate.model.FriendshipStatus;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    private final UserStorage storage;

    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public List<User> getAll() {
        return storage.getAll();
    }

    public User add(User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return storage.add(user);
    }

    public User update(User user) {
        return storage.update(user);
    }

    public User getById(long id) {
        return storage.getById(id);
    }

    /**
     * Добавление друга:
     */
    public void addFriend(long userId, long friendId) {
        log.info(String.valueOf(LogMessages.TRY_ADD_FRIEND));
        User user = getById(userId);
        User friend = getById(friendId);

        // если у друга уже есть этот пользователь
        if (friend.getFriends().containsKey(userId)) {
            user.getFriends().put(friendId, FriendshipStatus.CONFIRMED);
            friend.getFriends().put(userId, FriendshipStatus.CONFIRMED);
        } else {
            user.getFriends().put(friendId, FriendshipStatus.UNCONFIRMED);
            friend.getFriends().put(userId, FriendshipStatus.UNCONFIRMED);
        }
        log.info(String.valueOf(LogMessages.FRIEND_DONE), userId, friendId);
    }

    /**
     * Удаление из друзей:
     */
    public void removeFriend(long userId, long friendId) {
        log.info(String.valueOf(LogMessages.TRY_REMOVE_FRIEND));
        User user = getById(userId);
        User friend = getById(friendId);

        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
        log.info(String.valueOf(LogMessages.FRIEND_CANCEL), userId, friendId);

    }

    /**
     * Получить всех друзей пользователя
     */
    public List<User> getFriends(long userId) {
        log.info(String.valueOf(LogMessages.TRY_GET_FRIENDS));
        User user = getById(userId);
        return user.getFriends().keySet().stream()
                .map(this::getById)
                .collect(Collectors.toList());
    }

    /**
     * Получить общих друзей
     */
    public List<User> getCommonFriends(long userId, long otherId) {
        log.info(String.valueOf(LogMessages.TRY_GET_CORPORATE_FRIENDS));
        User user1 = getById(userId);
        User user2 = getById(otherId);
        log.info(String.valueOf(LogMessages.LIST_OF_FRIENDS));

        return user1.getFriends().keySet().stream()
                .filter(user2.getFriends()::containsKey)
                .map(this::getById)
                .collect(Collectors.toList());
    }

}


