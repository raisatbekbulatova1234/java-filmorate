package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.dao.UserStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
@Qualifier("InMemoryUserStorage")       //Используется для однозначности использования классов наследников интерфейса.
public class InMemoryUserStorage implements UserStorage {
    private final HashMap<Integer, User> users = new HashMap<>();

    /**
     * Получить список всех пользователей.
     */
    @Override
    public List<User> getAllUsersFromStorage() {
        return new ArrayList<>(users.values());
    }

    /**
     * Добавить пользователя в БД.
     */
    @Override
    public User addToStorage(User user) {
        users.put(user.getId(), user);
        return user;
    }


    /**
     * Обновить юзера в БД.
     */
    @Override
    public User updateInStorage(User user) {

        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    /**
     * Удалить пользователя из БД.
     */
    public User removeFromStorage(User user) {
        if (users.remove(user.getId(), user)) {
            return user;
        }
        return null;
    }

    /**
     * Получить пользователя по ID.
     */
    @Override
    public User getUserById(Integer id) {
        return users.getOrDefault(id, null);
    }

    /**
     * Получить пользователя по логину.
     */
    @Override
    public User getUserByLogin(String login) {
        return users.values().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }


    /**
     * Удалить пользователя из БД. True - удалено.
     */
    @Override
    public void removeFromStorage(Integer id) {
        //Когда-нибудь надо будет сделать.
    }

    /**
     * Проверка наличия юзера в БД.
     */
    @Override
    public boolean isExistUserInDB(Integer id) {
        return false;
    }
}